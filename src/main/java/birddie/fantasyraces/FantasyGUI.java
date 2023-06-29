package birddie.fantasyraces;

import com.mojang.blaze3d.matrix.MatrixStack;

import birddie.fantasyraces.race.CapabilityRace;
import birddie.fantasyraces.race.Race;
import birddie.fantasyraces.race.RacePacket;
import birddie.fantasyraces.race.RacePacket.RaceSyncPacket;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.list.OptionsRowList;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/*
 * Playable Fantasy Races
 * 
 * This class creates the GUI that is used to select your race.
 * 
 */
@OnlyIn(Dist.CLIENT)
public class FantasyGUI extends Screen{
	
	private FontRenderer fontRenderer;
	private PlayerEntity player;
	
	private int screen = 0;
	
	public FantasyGUI(ITextComponent title) {
		super(title);
	}
	
	public FantasyGUI(ITextComponent title, PlayerEntity player) {
		super(title);
		this.player = player;
	}


	@Override
	public void init() {
		super.init();
		fontRenderer = getMinecraft().font;
		
		this.addButton(new Button((this.width - (this.width/10) - this.width/20), (this.height/2), this.width/10, 20, new TranslationTextComponent("->"), (buttonWidget) -> {
			screen++;
			drawScreen();}));
		this.addButton(new Button((this.width/20), (this.height/2), this.width/10, 20, new TranslationTextComponent("<-"), (buttonWidget) -> {
			screen--;
			drawScreen();}));
		this.addButton(new Button((this.width/2-this.width/10), (this.height-20), this.width/5, 20, new TranslationTextComponent("Select Race"), (buttonWidget) -> {
			Race p = this.player.getCapability(CapabilityRace.RACE).orElse(null);
			if(screen == 0) {
				p.setRace(screen);
				this.player.closeContainer();
			}else if(screen == 1) {
				p.setRace(screen);
				this.player.closeContainer();
			}else if(screen == 2) {
				p.setRace(screen);
				this.player.closeContainer();
			}else if(screen == 3) {
				p.setRace(screen);
				this.player.closeContainer();
			}else {
				System.out.println("Something went wrong, no race selected");
			}
			RacePacket.CHANNEL.send(PacketDistributor.SERVER.noArg(), new RaceSyncPacket(p.getRace(), player.getUUID()));
		}));
		
		this.addWidget(new OptionsRowList(minecraft, screen, screen, screen, screen, screen));
	}
	
	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        switch(screen){
		case 0: drawHuman(matrixStack);
			break;
		case 1: drawDwarf(matrixStack);
			break;
		case 2: drawElf(matrixStack);
			break;
		case 3: drawHalfling(matrixStack);
		}
	}
	
	
	@Override
	public boolean isPauseScreen() {
		return false;
	}
	
	public void drawScreen() {
		if (screen == -1) {screen = 3;}
		if (screen == 4) {screen = 0;}
	}
	
	public void drawHuman(MatrixStack matrixStack){
		int centerX = this.width / 2;
	    int textY = this.height / 5;
	    
	    String line1 = "Human";
	    String line2 = "";
	    String line3 = "Default Minecraft experience";
	    String line4 = "No Gameplay Changes";
	    
	    int line1Width = fontRenderer.width(line1);
	    int line2Width = fontRenderer.width(line2);
	    int line3Width = fontRenderer.width(line3);
	    int line4Width = fontRenderer.width(line4);
	    
	    fontRenderer.draw(matrixStack, line1, centerX - line1Width / 2, textY, 0xFFFFFF);
	    fontRenderer.draw(matrixStack, line2, centerX - line2Width / 2, textY + fontRenderer.lineHeight, 0xFFFFFF);
	    fontRenderer.draw(matrixStack, line3, centerX - line3Width / 2, textY + 2 * fontRenderer.lineHeight, 0xFFFFFF);
	    fontRenderer.draw(matrixStack, line4, centerX - line4Width / 2, textY + 3 * fontRenderer.lineHeight, 0xFFFFFF);
	}
	
	public void drawDwarf(MatrixStack matrixStack){
		int centerX = this.width / 2;
	    int textY = this.height / 5;
	    
	    String line1 = "Dwarf";
	    String line2 = "";
	    String line3 = "Dwarves are smaller than humans, but not as small as halflings";
	    String line4 = "Dwarves mine faster the deeper they are";
	    String line5 = "Can see in the dark while underground";
	    String line6 = "Cannot be poisoned";
	    String line7 = "";
	    String line8 = "Hates starving";
	    String line9 = "Is terrible at farming";
	    String line10 = "Susceptible to drowning";
	    
	    int line1Width = fontRenderer.width(line1);
	    int line2Width = fontRenderer.width(line2);
	    int line3Width = fontRenderer.width(line3);
	    int line4Width = fontRenderer.width(line4);
	    int line5Width = fontRenderer.width(line5);
	    int line6Width = fontRenderer.width(line6);
	    int line7Width = fontRenderer.width(line7);
	    int line8Width = fontRenderer.width(line8);
	    int line9Width = fontRenderer.width(line9);
	    int line10Width = fontRenderer.width(line10);
	    
	    fontRenderer.draw(matrixStack, line1, centerX - line1Width / 2, textY, 0xFFFFFF);
	    fontRenderer.draw(matrixStack, line2, centerX - line2Width / 2, textY + fontRenderer.lineHeight, 0xFFFFFF);
	    fontRenderer.draw(matrixStack, line3, centerX - line3Width / 2, textY + 2 * fontRenderer.lineHeight, 0xFFFFFF);
	    fontRenderer.draw(matrixStack, line4, centerX - line4Width / 2, textY + 3 * fontRenderer.lineHeight, 0xFFFFFF);
	    fontRenderer.draw(matrixStack, line5, centerX - line5Width / 2, textY + 4 * fontRenderer.lineHeight, 0xFFFFFF);
	    fontRenderer.draw(matrixStack, line6, centerX - line6Width / 2, textY + 5 * fontRenderer.lineHeight, 0xFFFFFF);
	    fontRenderer.draw(matrixStack, line7, centerX - line7Width / 2, textY + 6 * fontRenderer.lineHeight, 0xFFFFFF);
	    fontRenderer.draw(matrixStack, line8, centerX - line8Width / 2, textY + 7 * fontRenderer.lineHeight, 0xFFFFFF);
	    fontRenderer.draw(matrixStack, line9, centerX - line9Width / 2, textY + 8 * fontRenderer.lineHeight, 0xFFFFFF);
	    fontRenderer.draw(matrixStack, line10, centerX - line10Width / 2, textY + 9 * fontRenderer.lineHeight, 0xFFFFFF);
	}


	public void drawElf(MatrixStack matrixStack){
		 int centerX = this.width / 2;
		    int textY = this.height / 5;
		    
		    String line1 = "Elf";
		    String line2 = "";
		    String line3 = "Slightly taller than humans";
		    String line4 = "Can see in the dark while above ground";
		    String line5 = "Deals more damage with a bow";
		    String line6 = "";
		    String line7 = "Mines slower underground";
		    String line8 = "Falls harder";
		    String line9 = "";
		    String line10 = "";
		    
		    int line1Width = fontRenderer.width(line1);
		    int line2Width = fontRenderer.width(line2);
		    int line3Width = fontRenderer.width(line3);
		    int line4Width = fontRenderer.width(line4);
		    int line5Width = fontRenderer.width(line5);
		    int line6Width = fontRenderer.width(line6);
		    int line7Width = fontRenderer.width(line7);
		    int line8Width = fontRenderer.width(line8);
		    int line9Width = fontRenderer.width(line9);
		    int line10Width = fontRenderer.width(line10);
		    
		    fontRenderer.draw(matrixStack, line1, centerX - line1Width / 2, textY, 0xFFFFFF);
		    fontRenderer.draw(matrixStack, line2, centerX - line2Width / 2, textY + fontRenderer.lineHeight, 0xFFFFFF);
		    fontRenderer.draw(matrixStack, line3, centerX - line3Width / 2, textY + 2 * fontRenderer.lineHeight, 0xFFFFFF);
		    fontRenderer.draw(matrixStack, line4, centerX - line4Width / 2, textY + 3 * fontRenderer.lineHeight, 0xFFFFFF);
		    fontRenderer.draw(matrixStack, line5, centerX - line5Width / 2, textY + 4 * fontRenderer.lineHeight, 0xFFFFFF);
		    fontRenderer.draw(matrixStack, line6, centerX - line6Width / 2, textY + 5 * fontRenderer.lineHeight, 0xFFFFFF);
		    fontRenderer.draw(matrixStack, line7, centerX - line7Width / 2, textY + 6 * fontRenderer.lineHeight, 0xFFFFFF);
		    fontRenderer.draw(matrixStack, line8, centerX - line8Width / 2, textY + 7 * fontRenderer.lineHeight, 0xFFFFFF);
		    fontRenderer.draw(matrixStack, line9, centerX - line9Width / 2, textY + 8 * fontRenderer.lineHeight, 0xFFFFFF);
		    fontRenderer.draw(matrixStack, line10, centerX - line10Width / 2, textY + 9 * fontRenderer.lineHeight, 0xFFFFFF);
	}
	
	public void drawHalfling(MatrixStack matrixStack){
		int centerX = this.width / 2;
	    int textY = this.height / 5;
	    
	    String line1 = "Halfling";
	    String line2 = "";
	    String line3 = "About 1 block tall";
	    String line4 = "Lucky";
	    String line5 = "Can dodge monster's attacks";
	    String line6 = "Cannot be withered";
	    String line7 = "";
	    String line8 = "Takes more damage";
	    String line9 = "";
	    String line10 = "";
	    
	    int line1Width = fontRenderer.width(line1);
	    int line2Width = fontRenderer.width(line2);
	    int line3Width = fontRenderer.width(line3);
	    int line4Width = fontRenderer.width(line4);
	    int line5Width = fontRenderer.width(line5);
	    int line6Width = fontRenderer.width(line6);
	    int line7Width = fontRenderer.width(line7);
	    int line8Width = fontRenderer.width(line8);
	    int line9Width = fontRenderer.width(line9);
	    int line10Width = fontRenderer.width(line10);
	    
	    fontRenderer.draw(matrixStack, line1, centerX - line1Width / 2, textY, 0xFFFFFF);
	    fontRenderer.draw(matrixStack, line2, centerX - line2Width / 2, textY + fontRenderer.lineHeight, 0xFFFFFF);
	    fontRenderer.draw(matrixStack, line3, centerX - line3Width / 2, textY + 2 * fontRenderer.lineHeight, 0xFFFFFF);
	    fontRenderer.draw(matrixStack, line4, centerX - line4Width / 2, textY + 3 * fontRenderer.lineHeight, 0xFFFFFF);
	    fontRenderer.draw(matrixStack, line5, centerX - line5Width / 2, textY + 4 * fontRenderer.lineHeight, 0xFFFFFF);
	    fontRenderer.draw(matrixStack, line6, centerX - line6Width / 2, textY + 5 * fontRenderer.lineHeight, 0xFFFFFF);
	    fontRenderer.draw(matrixStack, line7, centerX - line7Width / 2, textY + 6 * fontRenderer.lineHeight, 0xFFFFFF);
	    fontRenderer.draw(matrixStack, line8, centerX - line8Width / 2, textY + 7 * fontRenderer.lineHeight, 0xFFFFFF);
	    fontRenderer.draw(matrixStack, line9, centerX - line9Width / 2, textY + 8 * fontRenderer.lineHeight, 0xFFFFFF);
	    fontRenderer.draw(matrixStack, line10, centerX - line10Width / 2, textY + 9 * fontRenderer.lineHeight, 0xFFFFFF);
	}
	
	
}