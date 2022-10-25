package birddie.fantasyraces;

import java.io.IOException;

import birddie.fantasyraces.proxy.CommonProxy;
import birddie.fantasyraces.race.IRace;
import birddie.fantasyraces.race.RaceMessage;
import birddie.fantasyraces.race.RaceProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;

public class FantasyGUI extends GuiScreen{
	
	private GuiLabel fantasyRaces;
	private GuiButton humanButton;
	private GuiButton dwarfButton;
	private GuiButton elfButton;
	private GuiButton halflingButton;
	private FontRenderer fontRendererGui;
	private EntityPlayer player;
	
	public FantasyGUI(EntityPlayer player) {
		this.player = player;
	}

	@Override
	public void initGui() {
		super.initGui();
		fontRendererGui = Minecraft.getMinecraft().fontRenderer;
		this.labelList.add(fantasyRaces = new GuiLabel(fontRendererGui, 0, (this.width / 2)-150, (this.height/2)-40, 300, 20, 0xFFFFFF));
		this.buttonList.add(humanButton = new GuiButton(1,(this.width / 6), this.height - (this.height / 2), "Human"));
		humanButton.width=this.width/6;
		this.buttonList.add(dwarfButton = new GuiButton(2,(this.width / 6)*2, this.height - (this.height / 2), "Dwarf"));
		dwarfButton.width=this.width/6;
		this.buttonList.add(elfButton  = new GuiButton(3,(this.width / 6)*3, this.height - (this.height / 2), "Elf"));
		elfButton.width=this.width/6;
		this.buttonList.add(halflingButton = new GuiButton(4,(this.width / 6)*4, this.height - (this.height / 2), "Halfling"));
		halflingButton.width=this.width/6;
		
		fantasyRaces.setCentered();
		fantasyRaces.addLine("Select your race:");
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException{
		//clientside
		IRace p = this.player.getCapability(RaceProvider.RACE, null);
		if (button == humanButton){
			p.setRace(0);
			this.player.closeScreen();
		}
		if (button == dwarfButton){
			p.setRace(1);
			this.player.closeScreen();
		}
		if (button == elfButton){
			p.setRace(2);
			this.player.closeScreen();
		}
		if (button == halflingButton){
			p.setRace(3);
			this.player.closeScreen();
		}
		CommonProxy.NETWORK_TO_SERVER.sendToServer(new RaceMessage(p));
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
	
}
