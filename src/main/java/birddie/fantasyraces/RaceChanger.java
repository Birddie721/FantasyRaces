package birddie.fantasyraces;

import java.util.Random;

import birddie.fantasyraces.race.CapabilityRace;
import birddie.fantasyraces.race.Race;
import birddie.fantasyraces.race.RacePacket;
import birddie.fantasyraces.race.RacePacket.RaceSyncPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/*
 * Playable Fantasy Races
 * 
 * This class provides all of the racial changes for whichever selected race
 * 
 */
@Mod.EventBusSubscriber(modid=fantasyraces.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RaceChanger {
	
	double dodgeChance = .4;
	boolean dodged = false;
	
	
	public static void nonRaced(ServerPlayerEntity player) {
		Race p = player.getCapability(CapabilityRace.RACE).orElse(null);
		p.setRace(-1);
		RacePacket.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new RaceSyncPacket(p.getRace(), player.getUUID()));
	}
	
	
	  //Halflings can dodge attacks
	  //Dwarfs are immune to poison
	  //Halflings are immune to wither
	  //Halflings take increased damage
	  //Dwarfs take more starving and drowning damage
	  @SubscribeEvent(priority = EventPriority.HIGH) 
	  public void onDamageTaken(LivingDamageEvent event) {
		  if(!(event.getEntityLiving() instanceof PlayerEntity)) {
				return;
			}
		  Race p = event.getEntityLiving().getCapability(CapabilityRace.RACE).orElse(null);
		  Random rand = new Random();
		  if(dodgeChance >= rand.nextDouble() && p.getRace() == 3) {
				event.setCanceled(true);
				dodged = true;
		  }else if(event.getSource() == DamageSource.MAGIC && p.getRace() == 1) {
				event.setCanceled(true);
				dodged = true;
		  }else if(event.getSource() == DamageSource.WITHER && p.getRace() == 3) {
				event.setCanceled(true);
				dodged = true;
		  }else if(p.getRace() == 3) {
			  	event.setAmount(event.getAmount() * 1.5F);
		  }else if((p.getRace() == 2 || p.getRace() == 1) && event.getSource() == DamageSource.FALL) {
			  	event.setAmount(event.getAmount() * 1.5F);
		  }else if(p.getRace() == 1 && event.getSource() == DamageSource.STARVE) {
			  	event.setAmount(event.getAmount() * 2F);
		  }else if(p.getRace() == 1 && event.getSource() == DamageSource.DROWN) {
			  	event.setAmount(event.getAmount() * 2F);
		  }
	  }
	  
	  /*Halflings are less likely to be noticed by mobs
	  @SubscribeEvent
	  public void mobDetection(Visibility event) {
		  if(event.getEntityLiving() instanceof PlayerEntity) {
			  //IRace p = event.getEntityLiving().getCapability(RaceProvider.RACE, null);
			  //if(p.getRace() == 3) {
				  
			  //}
		  }
	  }*/
	  
	  //Elves deal increased damage with a bow
	  @SubscribeEvent(priority = EventPriority.LOW)
	  public void onBowDamage(LivingHurtEvent event) {
		  DamageSource source = event.getSource();
		  if(source.getEntity() != null && source.getEntity() instanceof PlayerEntity) {
			  Race p = source.getEntity().getCapability(CapabilityRace.RACE).orElse(null);
			  if(p.getRace() == 2) {
				  if (source.isProjectile()) {
					  event.setAmount(event.getAmount() * 1.25f);
				  }
			  }
		  }
	  }
	
	//When halflings dodge an attack, they do not get knocked back
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void doKnockback(LivingKnockBackEvent event) {
		if(!(event.getEntityLiving() instanceof PlayerEntity)) {
			return;
		}
		if(dodged == true) {
			event.setCanceled(true);
			dodged = false;
		}
	}
	
	//Dwarfs get night vision below Y=60
	//Elves get night vision above Y=60
	//Dwarfs are immune to poison
	//Dwarfs walk slower
	//Elves walk faster
	//Halflings are immune to wither
	//Halflings are lucky
	//Halflings are slower
	//Event done clientside and serverside
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event) {
		if(event.player.isDeadOrDying() == false) {
		Race p = event.player.getCapability(CapabilityRace.RACE).orElse(null);
		event.player.refreshDimensions();
		if((event.player instanceof ServerPlayerEntity)) {	
			//Serverside
			if(p.getRace() > -1) {
				RacePacket.CHANNEL.send(PacketDistributor.ALL.noArg(), new RaceSyncPacket(p.getRace(), event.player.getUUID()));
			}
		}else {
			//Clientside
		}
		
		if(!(event.player instanceof ServerPlayerEntity)) {	
			if(p.getRace() == -1) {
				openGUI(event.player);
			}
			if(event.player != null && !(event.player instanceof ServerPlayerEntity)){			
				if(event.player.getEntity() instanceof PlayerEntity) {
					if(event.player.getY() < 60 && p.getRace() == 1 /*&& Config.isPotionEnabled*/) {
						if(event.player.level.dimension() == World.OVERWORLD) {
							event.player.addEffect(new EffectInstance(Effects.NIGHT_VISION, 210, 0, false, false, false));
						}
					}
					if(event.player.getY() > 60 && p.getRace() == 2 /*&& Config.isPotionEnabled*/) {
						if(event.player.level.dimension() == World.OVERWORLD) {
							event.player.addEffect(new EffectInstance(Effects.NIGHT_VISION, 210, 0, false, false, false));
						}
					}
					if(p.getRace() == 0) {
					}
					if(p.getRace() == 1) {
						event.player.removeEffect(Effects.POISON);
					}
					if(p.getRace() == 2) {
					}
					if(p.getRace() == 3) {
						event.player.removeEffect(Effects.WITHER);
						event.player.addEffect(new EffectInstance(Effects.LUCK, 20, 0, false, false, false));
					}
				}
			}	
		}else {
			if(event.player != null){			
				if(event.player.getEntity() instanceof PlayerEntity) {
					if(event.player.getY() < 60 && p.getRace() == 1 /*&& Config.isPotionEnabled*/) {
						if(event.player.level.dimension() == World.OVERWORLD) {
							event.player.addEffect(new EffectInstance(Effects.NIGHT_VISION, 210, 0, false, false, false));
						}
					}
					if(event.player.getY() > 60 && p.getRace() == 2 /*&& Config.isPotionEnabled*/) {
						if(event.player.level.dimension() == World.OVERWORLD) {
							event.player.addEffect(new EffectInstance(Effects.NIGHT_VISION, 210, 0, false, false, false));
						}
					}
					if(p.getRace() == 0) {
					}
					if(p.getRace() == 1) {
						event.player.removeEffect(Effects.POISON);
					}
					if(p.getRace() == 2) {
					}
					if(p.getRace() == 3) {
						event.player.removeEffect(Effects.WITHER);
						event.player.addEffect(new EffectInstance(Effects.LUCK, 20, 0, false, false, false));
					}
				}
			}
		}
	}}
	@OnlyIn(Dist.CLIENT)
	public void openGUI(PlayerEntity player) {
		if(player != Minecraft.getInstance().player || player instanceof ServerPlayerEntity){
			return;
		}else {
			openFantasyGUI(player);
			Race p = player.getCapability(CapabilityRace.RACE).orElse(null);
			p.setRace(0);
			p.setRace(-2);
		}
	}
	
	//Dwarfs break blocks under Y=60 faster
	//Elves break blocks under Y=60 slower
	@SubscribeEvent
	public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
		if(!(event.getEntityLiving() instanceof PlayerEntity)) {
			return;
		}
		if(event.getEntityLiving().level.dimension() != World.OVERWORLD) {
			return;
		}
		Race p = event.getEntityLiving().getCapability(CapabilityRace.RACE).orElse(null);
		PlayerEntity player = event.getPlayer();
		if(p.getRace() == 1 && player.getY() < 60 ) {
			event.setNewSpeed((float) (event.getOriginalSpeed() * (1.7F + ((60 - player.getY())/50))));
		}
		if(p.getRace() == 2 && player.getY() < 60 ) {
			event.setNewSpeed(event.getOriginalSpeed() * 0.8F);
		}
	}
	
	//Dwarfs can't use bonemeal
	@SubscribeEvent
	public void onBonemeal(BonemealEvent event) {
		if(!(event.getEntityLiving() instanceof PlayerEntity)) {
			return;
		}
		Race p = event.getEntityLiving().getCapability(CapabilityRace.RACE).orElse(null);
		if(p.getRace() == 1) {
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void onPlayerClone(PlayerEvent.Clone event) {
		//serverside
		Race p = event.getPlayer().getCapability(CapabilityRace.RACE).orElse(null);
		Race q = event.getOriginal().getCapability(CapabilityRace.RACE).orElse(null);
		p.setRace(q.getRace());
		if(event.getPlayer() instanceof ServerPlayerEntity) {
			ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
			RacePacket.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new RaceSyncPacket(p.getRace(), player.getUUID()));
		}
	}
	
	@SubscribeEvent
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		//serverside
		Race p = event.getPlayer().getCapability(CapabilityRace.RACE).orElse(null);
		if(event.getPlayer() instanceof ServerPlayerEntity) {
			ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
			RacePacket.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new RaceSyncPacket(p.getRace(), player.getUUID()));
		}
	}
	
	@SubscribeEvent
	public void onPlayerChangeDimension(PlayerChangedDimensionEvent event) {
		//serverside
		Race p = event.getPlayer().getCapability(CapabilityRace.RACE).orElse(null);
		if(event.getPlayer() instanceof ServerPlayerEntity) {
			ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
			RacePacket.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new RaceSyncPacket(p.getRace(), player.getUUID()));
		}
	}
	@OnlyIn(Dist.CLIENT)
	public static void openFantasyGUI(PlayerEntity player) {
		Minecraft.getInstance().setScreen(new FantasyGUI(new TranslationTextComponent("gui.fantasyraces.fantasygui"), player));
	}
	
	
	
}
