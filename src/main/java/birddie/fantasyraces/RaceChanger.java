package birddie.fantasyraces;

import java.util.Random;
import java.util.UUID;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import birddie.fantasyraces.proxy.CommonProxy;
import birddie.fantasyraces.proxy.Config;
import birddie.fantasyraces.race.IRace;
import birddie.fantasyraces.race.RaceMessage;
import birddie.fantasyraces.race.RaceProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemAxe;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.Visibility;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/*
 * Playable Fantasy Races
 * 
 * This class provides all of the racial changes for whichever selected race
 * 
 */

public class RaceChanger {
	
	public static final UUID HEALTH_MODIFIER_ID = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479");
	public static final UUID SPEED_MODIFIER_ID = UUID.fromString("625f8d0c-5af6-4c46-88bf-ec370f1e30a1");
	public static final UUID LUCK_MODIFIER_ID = UUID.fromString("a726d61f-8b39-4f56-a23a-1c3b8ac73428");
	
	double dodgeChance = .4;
	boolean dodged = false;
	
	public static void nonRaced(EntityPlayer player) {
		IRace p = player.getCapability(RaceProvider.RACE, null);
		p.setRace(-1);
		CommonProxy.NETWORK_TO_CLIENT.sendTo(new RaceMessage(p, player), (EntityPlayerMP) player);
	}
	
	
	  //Halflings can dodge attacks
	  //Dwarfs are immune to poison
	  //Halflings are immune to wither
	  //Halflings take increased damage
	  //Dwarfs take more starving and drowning damage
	  @SubscribeEvent(priority = EventPriority.HIGH) 
	  public void onDamageTaken(LivingDamageEvent event) {
		  if(!(event.getEntityLiving() instanceof EntityPlayer)) {
				return;
			}
		  IRace p = event.getEntityLiving().getCapability(RaceProvider.RACE, null);
		  Random rand = new Random();
		  dodgeChance = Config.halflingDodgeChance;
		  if(dodgeChance >= rand.nextDouble() && p.getRace() == 3) {
				event.setCanceled(true);
				dodged = true;
		  }else if(event.getSource().isMagicDamage() && p.getRace() == 1 && Config.dwarfPoisonImmunity) {
				event.setCanceled(true);
				dodged = true;
		  }else if(event.getSource() == DamageSource.WITHER && p.getRace() == 3  && Config.halflingWitherImmunity) {
				event.setCanceled(true);
				dodged = true;
		  }else if(p.getRace() == 3) {
			  	event.setAmount(event.getAmount() * Config.halflingDamageMultiplier);
		  }else if((p.getRace() == 2 || p.getRace() == 1) && event.getSource() == DamageSource.FALL) {
			  	event.setAmount(event.getAmount() * Config.elfFallDamage);
		  }else if(p.getRace() == 1 && event.getSource() == DamageSource.STARVE) {
			  	event.setAmount(event.getAmount() * Config.dwarfHungerDamage);
		  }else if(p.getRace() == 1 && event.getSource() == DamageSource.DROWN) {
			  	event.setAmount(event.getAmount() * Config.dwarfDrownDamage);
		  }
	  }
	  
	  //Halflings are less likely to be noticed by mobs
	  @SubscribeEvent
	  public void mobDetection(Visibility event) {
		  if(event.getEntityLiving() instanceof EntityPlayer) {
			  //IRace p = event.getEntityLiving().getCapability(RaceProvider.RACE, null);
			  //if(p.getRace() == 3) {
				  
			  //}
		  }
	  }
	  
	  //Elves deal increased damage with a bow
	  @SubscribeEvent(priority = EventPriority.LOW)
	  public void onBowDamage(LivingHurtEvent event) {
		  DamageSource source = event.getSource();
		  if(source.getTrueSource() != null && source.getTrueSource() instanceof EntityPlayer) {
			  IRace p = source.getTrueSource().getCapability(RaceProvider.RACE, null);
			  if(p.getRace() == 2) {
				  if (source.isProjectile()) {
					  event.setAmount(event.getAmount() * Config.elfExtraBowDamage);
				  }
			  }else if(p.getRace() == 1) {
					if(event.getEntityLiving().getHeldItemMainhand().getItem() instanceof ItemAxe) {
						event.setAmount(event.getAmount() * Config.dwarfExtraAxeDamage);
					}
			  }
		  }
	  }
	
	//When halflings dodge an attack, they do not get knocked back
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void doKnockback(LivingKnockBackEvent event) {
		if(!(event.getEntityLiving() instanceof EntityPlayer)) {
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
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		IRace p = event.player.getCapability(RaceProvider.RACE, null);
		onSelectRaceAttributes(event.player);
		if((event.player instanceof EntityPlayerMP)) {	
			//Serverside
			
				if(p.getRace() > -1) {
					CommonProxy.NETWORK_TO_CLIENT.sendToAll(new RaceMessage(p, event.player));
				}
			
		}else {
			//Clientside
			
		}
		
		
		if(!(event.player instanceof EntityPlayerMP)) {	
			if(event.player != Minecraft.getMinecraft().player){
				//if on client side the event of the player is not the player playing
			}
			if(p.getRace() == -1) {
				openGUI(event.player);
				
			}
			if(event.player != null && !(event.player instanceof EntityPlayerMP)){			
				if(event.player.getEntityWorld().playerEntities.contains(event.player)) {
					if(event.player.posY < 60 && p.getRace() == 1 && Config.dwarfNightVision) {
						if(event.player.dimension == 0) {
							event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(16), 210, 0, false, false));
						}
					}
					if(event.player.posY > 60 && p.getRace() == 2 && Config.elfNightVision) {
						if(event.player.dimension == 0) {
							event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(16), 210, 0, false, false));
						}
					}
					if(p.getRace() == 0) {
					}
					if(p.getRace() == 1 && Config.dwarfPoisonImmunity) {
						event.player.removePotionEffect(Potion.getPotionById(19));
					}
					if(p.getRace() == 2) {
					}
					if(p.getRace() == 3 && Config.halflingWitherImmunity) {
						event.player.removePotionEffect(Potion.getPotionById(20));
					}
				}
			}	
		}else {
			if(event.player != null){			
				if(event.player.getEntityWorld().playerEntities.contains(event.player)) {
					if(event.player.posY < 60 && p.getRace() == 1 && Config.dwarfNightVision) {
						if(event.player.dimension == 0) {
							event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(16), 210, 0, false, false));
						}
					}
					if(event.player.posY > 60 && p.getRace() == 2 && Config.elfNightVision) {
						if(event.player.dimension == 0) {
							event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(16), 210, 0, false, false));
						}
					}
					if(p.getRace() == 0) {
					}
					if(p.getRace() == 1 && Config.dwarfPoisonImmunity) {
						event.player.removePotionEffect(Potion.getPotionById(19));
					}
					if(p.getRace() == 2) {
					}
					if(p.getRace() == 3 && Config.halflingWitherImmunity) {
						event.player.removePotionEffect(Potion.getPotionById(20) );
					}
				}
			}
		}
	}
	
	public void openGUI(EntityPlayer player) {
		if(player != Minecraft.getMinecraft().player){
			return;
		}else {
			player.openGui(fantasyraces.instance, 0, player.world, (int) player.posX, (int) player.posY, (int) player.posZ);
			IRace p = player.getCapability(RaceProvider.RACE, null);
			p.setRace(0);
			p.setRace(-2);
		}
	}
	
	//Dwarfs break blocks under Y=60 faster
	//Elves break blocks under Y=60 slower
	@SubscribeEvent
	public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
		if(!(event.getEntityLiving() instanceof EntityPlayer)) {
			return;
		}
		if(event.getEntityLiving().dimension != 0) {
			return;
		}
		IRace p = event.getEntityLiving().getCapability(RaceProvider.RACE, null);
		EntityPlayer player = event.getEntityPlayer();
		if(p.getRace() == 1 && player.posY < 60 ) {
			event.setNewSpeed((float) (event.getOriginalSpeed() * (Config.dwarfExtraMiningSpeed + ((60 - player.posY)/50))));
		}
		if(p.getRace() == 2 && player.posY < 60 ) {
			event.setNewSpeed(event.getOriginalSpeed() * Config.elfMiningSpeed);
		}
	}
	
	//Dwarfs can't use bonemeal
	@SubscribeEvent
	public void onBonemeal(BonemealEvent event) {
		if(!(event.getEntityLiving() instanceof EntityPlayer)) {
			return;
		}
		IRace p = event.getEntityLiving().getCapability(RaceProvider.RACE, null);
		if(p.getRace() == 1 && (Config.dwarfBonemeal==false)) {
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void onPlayerClone(PlayerEvent.Clone event) {
		//serverside
		IRace p = event.getEntityPlayer().getCapability(RaceProvider.RACE, null);
		IRace q = event.getOriginal().getCapability(RaceProvider.RACE, null);
		p.setRace(q.getRace());
		CommonProxy.NETWORK_TO_CLIENT.sendTo(new RaceMessage(p, event.getEntityPlayer()), (EntityPlayerMP) event.getEntityPlayer());
	}
	
	@SubscribeEvent
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		//serverside
		IRace p = event.player.getCapability(RaceProvider.RACE, null);
		CommonProxy.NETWORK_TO_CLIENT.sendTo(new RaceMessage(p, event.player), (EntityPlayerMP) event.player);
	}
	
	@SubscribeEvent
	public void onPlayerChangeDimension(PlayerChangedDimensionEvent event) {
		//serverside
		IRace p = event.player.getCapability(RaceProvider.RACE, null);
		CommonProxy.NETWORK_TO_CLIENT.sendTo(new RaceMessage(p, event.player), (EntityPlayerMP) event.player);
	}
	
	public void onSelectRaceAttributes(EntityPlayer player){
		Double speed = player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue();
		Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();
		IRace p = player.getCapability(RaceProvider.RACE, null);
		
		AttributeModifier healthModifier = new AttributeModifier(HEALTH_MODIFIER_ID, "Health Mod",p.getRace()==1?Config.dwarfExtraHearts:0.0, 0);
		AttributeModifier speedModifier = new AttributeModifier(SPEED_MODIFIER_ID, "Speed Mod",(speed*(p.getRace()==2?Config.elfMovementSpeed:1.0))-speed, 0);
		AttributeModifier luckModifier = new AttributeModifier(LUCK_MODIFIER_ID, "Luck Mod",(p.getRace()==3?Config.halflingLuck:0),0);
		
		multimap.put(SharedMonsterAttributes.MAX_HEALTH.getName(), healthModifier);
		multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), speedModifier);
		multimap.put(SharedMonsterAttributes.LUCK.getName(), luckModifier);
		player.getAttributeMap().applyAttributeModifiers(multimap);
	}
	
	
}
