package birddie.fantasyraces;

import java.util.Random;

import birddie.fantasyraces.proxy.CommonProxy;
import birddie.fantasyraces.race.IRace;
import birddie.fantasyraces.race.RaceMessage;
import birddie.fantasyraces.race.RaceProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class RaceChanger {
	
	double dodgeChance = .4;
	boolean dodged = false;
	
	
	public static void nonRaced(EntityPlayer player) {
		System.out.println(player.getName() + "is NONRACED!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + player.getName());
		IRace p = player.getCapability(RaceProvider.RACE, null);
		p.setRace(-1);
		CommonProxy.NETWORK_TO_CLIENT.sendTo(new RaceMessage(p), (EntityPlayerMP) player);
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
		  if(dodgeChance >= rand.nextDouble() && p.getRace() == 3) {
				event.setCanceled(true);
				dodged = true;
		  }else if(event.getSource().isMagicDamage() && p.getRace() == 1) {
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
	  
	  //Halflings are less likely to be noticed by mobs
	  @SubscribeEvent
	  public void mobDetection(LivingSpawnEvent event) {
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
					  event.setAmount(event.getAmount() * 1.5f);
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
	//Event done clientside
	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		IRace p = event.player.getCapability(RaceProvider.RACE, null);
		if((event.player instanceof EntityPlayerMP)) {	
			//System.out.println(event.player.getName() + " serverside race: " + p.getRace());
			
			//Sloppy fix, but works for now
			//CommonProxy.NETWORK_TO_CLIENT.sendTo(new RaceMessage(p), (EntityPlayerMP) event.player);
		}else {
			//System.out.println(event.player.getName() + " clientside race: " + p.getRace());
		}
		
		if(!(event.player instanceof EntityPlayerMP)) {	
			if(p.getRace() == -1) {
				openGUI(event.player);
			}else {
				//event.player.closeScreen();
			}
			if(event.player != null && !(event.player instanceof EntityPlayerMP)){			
				if(event.player.getEntityWorld().playerEntities.contains(event.player)) {
					if(event.player.posY < 60 && p.getRace() == 1) {
						event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(16), 420));
					}else if(p.getRace() == 1){event.player.removePotionEffect(Potion.getPotionById(16));}
					if(event.player.posY > 60 && p.getRace() == 2) {
						event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(16), 420));
					}else if(p.getRace() ==2){event.player.removePotionEffect(Potion.getPotionById(16));}
					if(p.getRace() == 0) {
						//event.player.capabilities.setPlayerWalkSpeed(0.1f);
					}
					if(p.getRace() == 1) {
						event.player.removePotionEffect(Potion.getPotionById(19));
						//event.player.capabilities.setPlayerWalkSpeed(0.08f);
					}
					if(p.getRace() == 2) {
						//event.player.capabilities.setPlayerWalkSpeed(0.12f);
					}
					if(p.getRace() == 3) {
						event.player.removePotionEffect(Potion.getPotionById(20));
						event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(26), 20));
						//event.player.capabilities.setPlayerWalkSpeed(0.08f);
					}else {event.player.removePotionEffect(Potion.getPotionById(26));}
				}
			}	
		}else {
			if(event.player != null){			
				if(event.player.getEntityWorld().playerEntities.contains(event.player)) {
					if(event.player.posY < 60 && p.getRace() == 1) {
						event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(16), 420));
					}else if(p.getRace() == 1){event.player.removePotionEffect(Potion.getPotionById(16));}
					if(event.player.posY > 60 && p.getRace() == 2) {
						event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(16), 420));
					}else if(p.getRace() ==2){event.player.removePotionEffect(Potion.getPotionById(16));}
					if(p.getRace() == 0) {
					}
					if(p.getRace() == 1) {
						event.player.removePotionEffect(Potion.getPotionById(19));
					}
					if(p.getRace() == 2) {
					}
					if(p.getRace() == 3) {
						event.player.removePotionEffect(Potion.getPotionById(20));
						event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(26), 20));
					}else {event.player.removePotionEffect(Potion.getPotionById(26));}
				}
			}
		}
	}
	
	public void openGUI(EntityPlayer player) {
		if(player != Minecraft.getMinecraft().player) {return;}
		//System.out.println("Opening GUI for " + player.getName());
		player.openGui(fantasyraces.instance, 0, player.world, (int) player.posX, (int) player.posY, (int) player.posZ);
		IRace p = player.getCapability(RaceProvider.RACE, null);
		p.setRace(0);
		p.setRace(-2);
	}
	
	//Dwarfs break blocks under Y=60 faster
	//Elves break blocks under Y=60 slower
	@SubscribeEvent
	public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
		if(!(event.getEntityLiving() instanceof EntityPlayer)) {
			return;
		}
		IRace p = event.getEntityLiving().getCapability(RaceProvider.RACE, null);
		EntityPlayer player = event.getEntityPlayer();
		if(p.getRace() == 1 && player.posY < 60 ) {
			event.setNewSpeed(event.getOriginalSpeed() * 1.7F);
		}
		if(p.getRace() == 2 && player.posY < 60 ) {
			event.setNewSpeed(event.getOriginalSpeed() * 0.8F);
		}
	}
	
	//Dwarfs can't use bonemeal
	@SubscribeEvent
	public void onBonemeal(BonemealEvent event) {
		if(!(event.getEntityLiving() instanceof EntityPlayer)) {
			return;
		}
		IRace p = event.getEntityLiving().getCapability(RaceProvider.RACE, null);
		if(p.getRace() == 1) {
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void onPlayerClone(PlayerEvent.Clone event) {
		//serverside
		IRace p = event.getEntityPlayer().getCapability(RaceProvider.RACE, null);
		IRace q = event.getOriginal().getCapability(RaceProvider.RACE, null);
		//System.out.println("Found player of race " + q.getRace());
		p.setRace(q.getRace());
		//System.out.println("New player will spawn as "+p.getRace());
		CommonProxy.NETWORK_TO_CLIENT.sendTo(new RaceMessage(p), (EntityPlayerMP) event.getEntityPlayer());
		//System.out.println("Cloned Player");
	}
	
	@SubscribeEvent
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		//serverside
		IRace p = event.player.getCapability(RaceProvider.RACE, null);
		CommonProxy.NETWORK_TO_CLIENT.sendTo(new RaceMessage(p), (EntityPlayerMP) event.player);
		//System.out.println(event.player.getName() + " respawned as race " + p.getRace());
	}
	
	@SubscribeEvent
	public void onPlayerChangeDimension(PlayerChangedDimensionEvent event) {
		//serverside
		IRace p = event.player.getCapability(RaceProvider.RACE, null);
		CommonProxy.NETWORK_TO_CLIENT.sendTo(new RaceMessage(p), (EntityPlayerMP) event.player);
		//System.out.println(event.player.getName() + " changed dimensions as race " + p.getRace());
	}
	
}
