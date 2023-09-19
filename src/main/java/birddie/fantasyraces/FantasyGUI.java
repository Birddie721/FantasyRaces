package birddie.fantasyraces;

import java.io.IOException;

import birddie.fantasyraces.proxy.CommonProxy;
import birddie.fantasyraces.proxy.Config;
import birddie.fantasyraces.race.AdvancementEffectMessage;
import birddie.fantasyraces.race.IRace;
import birddie.fantasyraces.race.NewRaceMessage;
import birddie.fantasyraces.race.RaceProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

/*
 * Playable Fantasy Races
 * 
 * This class creates the GUI that is used to select your race.
 * 
 */

public class FantasyGUI extends GuiScreen{
	
	private GuiLabel fantasyRaces;
	
	private GuiButton btnSelect;
	private GuiButton btnLeft;
	private GuiButton btnRight;
	
	private FontRenderer fontRendererGui;
	private EntityPlayer player;
	
	private int screen = 0;
	
	public FantasyGUI(EntityPlayer player) {
		this.player = player;
	}

	@Override
	public void initGui() {
		super.initGui();
		fontRendererGui = Minecraft.getMinecraft().fontRenderer;
		
		drawDefault();
		
		this.buttonList.add(btnRight = new GuiButton(1, (this.width - (this.width/10) - this.width/20), (this.height/2), "->"));
		btnRight.width = this.width/10;
		
		this.buttonList.add(btnLeft = new GuiButton(2, (this.width/20), (this.height/2), "<-"));
		btnLeft.width = this.width/10;
		
		this.buttonList.add(btnSelect = new GuiButton(3, (this.width/2-this.width/10), (this.height-20), "Select Race"));
		btnSelect.width = this.width/5;
		
		this.labelList.add(fantasyRaces = new GuiLabel(fontRendererGui, 0, (this.width/20), (this.height/5) + (this.height/10), 300, 20, 0xFFFFFF));
		fantasyRaces.setCentered();
		drawHuman();
		
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException{
		//clientside
		this.labelList.clear();
		IRace p = this.player.getCapability(RaceProvider.RACE, null);
		if (button == btnRight){
			screen++;
		}
		if (button == btnLeft){
			screen--;
		}
		if (button == btnSelect){
			if(screen == 0) {
				p.setRace(screen);
				advancementSpeed(this.player);
				this.player.closeScreen();
				
			}else if(screen == 1 && Config.isDwarfEnabled == true) {
				p.setRace(screen);
				advancementSpeed(this.player);
				this.player.closeScreen();
			}else if(screen == 2 && Config.isElfEnabled == true) {
				p.setRace(screen);
				advancementSpeed(this.player);
				this.player.closeScreen();
				
			}else if(screen == 3 && Config.isHalflingEnabled == true) {
				p.setRace(screen);
				advancementSpeed(this.player);
				this.player.closeScreen();
				
			}else {
				this.labelList.add(fantasyRaces = new GuiLabel(fontRendererGui, 0, (this.width / 2)-150, (this.height/10 * 8), 300, 20, 0xFFFFFF));
				fantasyRaces.setCentered();
				fantasyRaces.addLine(I18n.format("fantasyraces.raceDisabled"));
			}
			
		}
		if (screen == 4) {screen = 0;}
		if (screen == -1) {screen = 3;}
		drawDefault();
		switch(screen){
			case 0: drawHuman();
				break;
			case 1: drawDwarf();
				break;
			case 2: drawElf();
				break;
			case 3: drawHalfling();
		}
		CommonProxy.NETWORK_TO_SERVER.sendToServer(new NewRaceMessage(p));
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	public void drawDefault() {
		this.labelList.add(fantasyRaces = new GuiLabel(fontRendererGui, 0, (this.width / 2)-150, (this.height/10), 300, 20, 0xFFFFFF));
		fantasyRaces.setCentered();
		fantasyRaces.addLine(I18n.format("fantasyraces.raceSelect"));
	}
	
	public void drawHuman(){
		this.labelList.add(fantasyRaces = new GuiLabel(fontRendererGui, 0, (this.width/2)-150, (2* this.height/5), 300, 20, 0xFFFFFF));
		fantasyRaces.setCentered();
		fantasyRaces.addLine(I18n.format("fantasyraces.Human.1"));
		fantasyRaces.addLine("");
		fantasyRaces.addLine(I18n.format("fantasyraces.Human.2"));
		fantasyRaces.addLine(I18n.format("fantasyraces.Human.3"));
		fantasyRaces.addLine("");
		fantasyRaces.addLine("");
		fantasyRaces.addLine("");
		fantasyRaces.addLine("");
		fantasyRaces.addLine("");
		fantasyRaces.addLine("");
	}
	
	public void drawDwarf(){
		this.labelList.add(fantasyRaces = new GuiLabel(fontRendererGui, 0, (this.width/2)-150, (2* this.height/5), 300, 20, 0xFFFFFF));
		fantasyRaces.setCentered();
		fantasyRaces.addLine(I18n.format("fantasyraces.Dwarf.1"));
		fantasyRaces.addLine("");
		fantasyRaces.addLine(I18n.format("fantasyraces.Dwarf.2"));
		fantasyRaces.addLine(I18n.format("fantasyraces.Dwarf.3"));
		fantasyRaces.addLine(I18n.format("fantasyraces.Dwarf.4"));
		fantasyRaces.addLine(I18n.format("fantasyraces.Dwarf.5"));
		fantasyRaces.addLine("");
		fantasyRaces.addLine(I18n.format("fantasyraces.Dwarf.6"));
		fantasyRaces.addLine(I18n.format("fantasyraces.Dwarf.7"));
		fantasyRaces.addLine(I18n.format("fantasyraces.Dwarf.8"));
	}


	public void drawElf(){
		this.labelList.add(fantasyRaces = new GuiLabel(fontRendererGui, 0, (this.width/2)-150, (2* this.height/5), 300, 20, 0xFFFFFF));
		fantasyRaces.setCentered();
		fantasyRaces.addLine(I18n.format("fantasyraces.Elf.1"));
		fantasyRaces.addLine("");
		fantasyRaces.addLine(I18n.format("fantasyraces.Elf.2"));
		fantasyRaces.addLine(I18n.format("fantasyraces.Elf.3"));
		fantasyRaces.addLine(I18n.format("fantasyraces.Elf.4"));
		fantasyRaces.addLine("");
		fantasyRaces.addLine(I18n.format("fantasyraces.Elf.5"));
		fantasyRaces.addLine(I18n.format("fantasyraces.Elf.6"));
		fantasyRaces.addLine("");
		fantasyRaces.addLine("");
	}
	
	public void drawHalfling(){
		this.labelList.add(fantasyRaces = new GuiLabel(fontRendererGui, 0, (this.width/2)-150, (2* this.height/5), 300, 20, 0xFFFFFF));
		fantasyRaces.setCentered();
		fantasyRaces.addLine(I18n.format("fantasyraces.Halfling.1"));
		fantasyRaces.addLine("");
		fantasyRaces.addLine(I18n.format("fantasyraces.Halfling.2"));
		fantasyRaces.addLine(I18n.format("fantasyraces.Halfling.3"));
		fantasyRaces.addLine(I18n.format("fantasyraces.Halfling.4"));
		fantasyRaces.addLine(I18n.format("fantasyraces.Halfling.5"));
		fantasyRaces.addLine("");
		fantasyRaces.addLine(I18n.format("fantasyraces.Halfling.6"));
		fantasyRaces.addLine("");
		fantasyRaces.addLine("");
	}
	
	public void advancementSpeed(EntityPlayer player) {
		//player.addPotionEffect(new PotionEffect(Potion.getPotionById(1), 10, 0, false, false));
		CommonProxy.ADVANCEMENT_MESSAGE.sendToServer(new AdvancementEffectMessage(player));
	}
	
	
}