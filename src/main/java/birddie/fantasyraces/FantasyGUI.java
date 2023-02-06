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
		IRace p = this.player.getCapability(RaceProvider.RACE, null);
		if (button == btnRight){
			screen++;
		}
		if (button == btnLeft){
			screen--;
		}
		if (button == btnSelect){
			p.setRace(screen);
			this.player.closeScreen();
		}
		if (screen == 4) {screen = 0;}
		if (screen == -1) {screen = 3;}
		System.out.println(screen);
		this.labelList.clear();
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
	
	public void drawDefault() {
		this.labelList.add(fantasyRaces = new GuiLabel(fontRendererGui, 0, (this.width / 2)-150, (this.height/10), 300, 20, 0xFFFFFF));
		fantasyRaces.setCentered();
		fantasyRaces.addLine("Select your race:");
	}
	
	public void drawHuman(){
		this.labelList.add(fantasyRaces = new GuiLabel(fontRendererGui, 0, (this.width/2)-150, (2* this.height/5), 300, 20, 0xFFFFFF));
		fantasyRaces.setCentered();
		fantasyRaces.addLine("Human");
		fantasyRaces.addLine("");
		fantasyRaces.addLine("Default Minecraft experience");
		fantasyRaces.addLine("No Gameplay Changes");
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
		fantasyRaces.addLine("Dwarf");
		fantasyRaces.addLine("");
		fantasyRaces.addLine("Dwarves are smaller than humans, but not as small as halflings");
		fantasyRaces.addLine("Dwarves mine faster the deeper they are");
		fantasyRaces.addLine("Can see in the dark while underground");
		fantasyRaces.addLine("Cannot be poisoned");
		fantasyRaces.addLine("");
		fantasyRaces.addLine("Hates starving");
		fantasyRaces.addLine("Is terrible at farming");
		fantasyRaces.addLine("Susceptible to drowning");
	}


	public void drawElf(){
		this.labelList.add(fantasyRaces = new GuiLabel(fontRendererGui, 0, (this.width/2)-150, (2* this.height/5), 300, 20, 0xFFFFFF));
		fantasyRaces.setCentered();
		fantasyRaces.addLine("Elf");
		fantasyRaces.addLine("");
		fantasyRaces.addLine("Slightly taller than humans");
		fantasyRaces.addLine("Can see in the dark while above ground");
		fantasyRaces.addLine("Deals more damage with a bow");
		fantasyRaces.addLine("");
		fantasyRaces.addLine("Mines slower underground");
		fantasyRaces.addLine("Falls harder");
		fantasyRaces.addLine("");
		fantasyRaces.addLine("");
	}
	
	public void drawHalfling(){
		this.labelList.add(fantasyRaces = new GuiLabel(fontRendererGui, 0, (this.width/2)-150, (2* this.height/5), 300, 20, 0xFFFFFF));
		fantasyRaces.setCentered();
		fantasyRaces.addLine("Halfling");
		fantasyRaces.addLine("");
		fantasyRaces.addLine("About 1 block tall");
		fantasyRaces.addLine("Lucky");
		fantasyRaces.addLine("Can dodge monster's attacks");
		fantasyRaces.addLine("Cannot be withered");
		fantasyRaces.addLine("");
		fantasyRaces.addLine("Takes more damage");
		fantasyRaces.addLine("");
		fantasyRaces.addLine("");
	}
	
	
}