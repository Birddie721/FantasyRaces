package birddie.fantasyraces.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.GlStateManager;

/**
 * ModelPlayer - Either Mojang or a mod author
 * Created using Tabula 7.1.0
 */
public class Halfling extends ModelBase {
    public ModelRenderer field_178736_x;
    public ModelRenderer field_178729_w;
    public ModelRenderer OverLArm;
    public ModelRenderer OverRLeg;
    public ModelRenderer field_178732_b;
    public ModelRenderer Mask;
    public ModelRenderer OverLLeg;
    public ModelRenderer RArm;
    public ModelRenderer RLeg;
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer LArm;
    public ModelRenderer LLeg;
    public ModelRenderer OverBody;

    public Halfling() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.RArm = new ModelRenderer(this, 40, 16);
        this.RArm.setRotationPoint(-2.5F, 13.0F, 0.0F);
        this.RArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.field_178732_b = new ModelRenderer(this, 40, 32);
        this.field_178732_b.setRotationPoint(-2.5F, 13.0F, 10.0F);
        this.field_178732_b.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
        this.OverRLeg = new ModelRenderer(this, 0, 32);
        this.OverRLeg.setRotationPoint(-1.0F, 18.0F, 0.0F);
        this.OverRLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
        this.LArm = new ModelRenderer(this, 32, 48);
        this.LArm.setRotationPoint(2.5F, 13.0F, 0.0F);
        this.LArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.OverBody = new ModelRenderer(this, 16, 32);
        this.OverBody.setRotationPoint(0.0F, 12.0F, 0.0F);
        this.OverBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.25F);
        this.field_178736_x = new ModelRenderer(this, 24, 0);
        this.field_178736_x.setRotationPoint(0.0F, 12.0F, 0.0F);
        this.field_178736_x.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, 0.0F);
        this.Mask = new ModelRenderer(this, 32, 0);
        this.Mask.setRotationPoint(0.0F, 12.0F, 0.0F);
        this.Mask.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
        this.OverLLeg = new ModelRenderer(this, 0, 48);
        this.OverLLeg.setRotationPoint(1.0F, 18.0F, 0.0F);
        this.OverLLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
        this.Body = new ModelRenderer(this, 16, 16);
        this.Body.setRotationPoint(0.0F, 12.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.RLeg = new ModelRenderer(this, 0, 16);
        this.RLeg.setRotationPoint(-1.0F, 18.0F, 0.0F);
        this.RLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.field_178729_w = new ModelRenderer(this, 0, 0);
        this.field_178729_w.setRotationPoint(0.0F, 12.0F, 0.0F);
        this.field_178729_w.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, 0.0F);
        this.OverLArm = new ModelRenderer(this, 48, 48);
        this.OverLArm.setRotationPoint(2.5F, 13.0F, 0.0F);
        this.OverLArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 12.0F, 0.0F);
        this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.LLeg = new ModelRenderer(this, 16, 48);
        this.LLeg.setRotationPoint(1.0F, 18.0F, 0.0F);
        this.LLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.RArm.offsetX, this.RArm.offsetY, this.RArm.offsetZ);
        GlStateManager.translate(this.RArm.rotationPointX * f5, this.RArm.rotationPointY * f5, this.RArm.rotationPointZ * f5);
        GlStateManager.scale(0.5D, 0.5D, 0.5D);
        GlStateManager.translate(-this.RArm.offsetX, -this.RArm.offsetY, -this.RArm.offsetZ);
        GlStateManager.translate(-this.RArm.rotationPointX * f5, -this.RArm.rotationPointY * f5, -this.RArm.rotationPointZ * f5);
        this.RArm.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.field_178732_b.offsetX, this.field_178732_b.offsetY, this.field_178732_b.offsetZ);
        GlStateManager.translate(this.field_178732_b.rotationPointX * f5, this.field_178732_b.rotationPointY * f5, this.field_178732_b.rotationPointZ * f5);
        GlStateManager.scale(0.5D, 0.5D, 0.5D);
        GlStateManager.translate(-this.field_178732_b.offsetX, -this.field_178732_b.offsetY, -this.field_178732_b.offsetZ);
        GlStateManager.translate(-this.field_178732_b.rotationPointX * f5, -this.field_178732_b.rotationPointY * f5, -this.field_178732_b.rotationPointZ * f5);
        this.field_178732_b.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.OverRLeg.offsetX, this.OverRLeg.offsetY, this.OverRLeg.offsetZ);
        GlStateManager.translate(this.OverRLeg.rotationPointX * f5, this.OverRLeg.rotationPointY * f5, this.OverRLeg.rotationPointZ * f5);
        GlStateManager.scale(0.5D, 0.5D, 0.5D);
        GlStateManager.translate(-this.OverRLeg.offsetX, -this.OverRLeg.offsetY, -this.OverRLeg.offsetZ);
        GlStateManager.translate(-this.OverRLeg.rotationPointX * f5, -this.OverRLeg.rotationPointY * f5, -this.OverRLeg.rotationPointZ * f5);
        this.OverRLeg.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.LArm.offsetX, this.LArm.offsetY, this.LArm.offsetZ);
        GlStateManager.translate(this.LArm.rotationPointX * f5, this.LArm.rotationPointY * f5, this.LArm.rotationPointZ * f5);
        GlStateManager.scale(0.5D, 0.5D, 0.5D);
        GlStateManager.translate(-this.LArm.offsetX, -this.LArm.offsetY, -this.LArm.offsetZ);
        GlStateManager.translate(-this.LArm.rotationPointX * f5, -this.LArm.rotationPointY * f5, -this.LArm.rotationPointZ * f5);
        this.LArm.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.OverBody.offsetX, this.OverBody.offsetY, this.OverBody.offsetZ);
        GlStateManager.translate(this.OverBody.rotationPointX * f5, this.OverBody.rotationPointY * f5, this.OverBody.rotationPointZ * f5);
        GlStateManager.scale(0.5D, 0.5D, 0.5D);
        GlStateManager.translate(-this.OverBody.offsetX, -this.OverBody.offsetY, -this.OverBody.offsetZ);
        GlStateManager.translate(-this.OverBody.rotationPointX * f5, -this.OverBody.rotationPointY * f5, -this.OverBody.rotationPointZ * f5);
        this.OverBody.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.field_178736_x.offsetX, this.field_178736_x.offsetY, this.field_178736_x.offsetZ);
        GlStateManager.translate(this.field_178736_x.rotationPointX * f5, this.field_178736_x.rotationPointY * f5, this.field_178736_x.rotationPointZ * f5);
        GlStateManager.scale(0.5D, 0.5D, 0.5D);
        GlStateManager.translate(-this.field_178736_x.offsetX, -this.field_178736_x.offsetY, -this.field_178736_x.offsetZ);
        GlStateManager.translate(-this.field_178736_x.rotationPointX * f5, -this.field_178736_x.rotationPointY * f5, -this.field_178736_x.rotationPointZ * f5);
        this.field_178736_x.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.Mask.offsetX, this.Mask.offsetY, this.Mask.offsetZ);
        GlStateManager.translate(this.Mask.rotationPointX * f5, this.Mask.rotationPointY * f5, this.Mask.rotationPointZ * f5);
        GlStateManager.scale(0.5D, 0.5D, 0.5D);
        GlStateManager.translate(-this.Mask.offsetX, -this.Mask.offsetY, -this.Mask.offsetZ);
        GlStateManager.translate(-this.Mask.rotationPointX * f5, -this.Mask.rotationPointY * f5, -this.Mask.rotationPointZ * f5);
        this.Mask.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.OverLLeg.offsetX, this.OverLLeg.offsetY, this.OverLLeg.offsetZ);
        GlStateManager.translate(this.OverLLeg.rotationPointX * f5, this.OverLLeg.rotationPointY * f5, this.OverLLeg.rotationPointZ * f5);
        GlStateManager.scale(0.5D, 0.5D, 0.5D);
        GlStateManager.translate(-this.OverLLeg.offsetX, -this.OverLLeg.offsetY, -this.OverLLeg.offsetZ);
        GlStateManager.translate(-this.OverLLeg.rotationPointX * f5, -this.OverLLeg.rotationPointY * f5, -this.OverLLeg.rotationPointZ * f5);
        this.OverLLeg.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.Body.offsetX, this.Body.offsetY, this.Body.offsetZ);
        GlStateManager.translate(this.Body.rotationPointX * f5, this.Body.rotationPointY * f5, this.Body.rotationPointZ * f5);
        GlStateManager.scale(0.5D, 0.5D, 0.5D);
        GlStateManager.translate(-this.Body.offsetX, -this.Body.offsetY, -this.Body.offsetZ);
        GlStateManager.translate(-this.Body.rotationPointX * f5, -this.Body.rotationPointY * f5, -this.Body.rotationPointZ * f5);
        this.Body.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.RLeg.offsetX, this.RLeg.offsetY, this.RLeg.offsetZ);
        GlStateManager.translate(this.RLeg.rotationPointX * f5, this.RLeg.rotationPointY * f5, this.RLeg.rotationPointZ * f5);
        GlStateManager.scale(0.5D, 0.5D, 0.5D);
        GlStateManager.translate(-this.RLeg.offsetX, -this.RLeg.offsetY, -this.RLeg.offsetZ);
        GlStateManager.translate(-this.RLeg.rotationPointX * f5, -this.RLeg.rotationPointY * f5, -this.RLeg.rotationPointZ * f5);
        this.RLeg.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.field_178729_w.offsetX, this.field_178729_w.offsetY, this.field_178729_w.offsetZ);
        GlStateManager.translate(this.field_178729_w.rotationPointX * f5, this.field_178729_w.rotationPointY * f5, this.field_178729_w.rotationPointZ * f5);
        GlStateManager.scale(0.5D, 0.5D, 0.5D);
        GlStateManager.translate(-this.field_178729_w.offsetX, -this.field_178729_w.offsetY, -this.field_178729_w.offsetZ);
        GlStateManager.translate(-this.field_178729_w.rotationPointX * f5, -this.field_178729_w.rotationPointY * f5, -this.field_178729_w.rotationPointZ * f5);
        this.field_178729_w.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.OverLArm.offsetX, this.OverLArm.offsetY, this.OverLArm.offsetZ);
        GlStateManager.translate(this.OverLArm.rotationPointX * f5, this.OverLArm.rotationPointY * f5, this.OverLArm.rotationPointZ * f5);
        GlStateManager.scale(0.5D, 0.5D, 0.5D);
        GlStateManager.translate(-this.OverLArm.offsetX, -this.OverLArm.offsetY, -this.OverLArm.offsetZ);
        GlStateManager.translate(-this.OverLArm.rotationPointX * f5, -this.OverLArm.rotationPointY * f5, -this.OverLArm.rotationPointZ * f5);
        this.OverLArm.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.Head.offsetX, this.Head.offsetY, this.Head.offsetZ);
        GlStateManager.translate(this.Head.rotationPointX * f5, this.Head.rotationPointY * f5, this.Head.rotationPointZ * f5);
        GlStateManager.scale(0.5D, 0.5D, 0.5D);
        GlStateManager.translate(-this.Head.offsetX, -this.Head.offsetY, -this.Head.offsetZ);
        GlStateManager.translate(-this.Head.rotationPointX * f5, -this.Head.rotationPointY * f5, -this.Head.rotationPointZ * f5);
        this.Head.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.LLeg.offsetX, this.LLeg.offsetY, this.LLeg.offsetZ);
        GlStateManager.translate(this.LLeg.rotationPointX * f5, this.LLeg.rotationPointY * f5, this.LLeg.rotationPointZ * f5);
        GlStateManager.scale(0.5D, 0.5D, 0.5D);
        GlStateManager.translate(-this.LLeg.offsetX, -this.LLeg.offsetY, -this.LLeg.offsetZ);
        GlStateManager.translate(-this.LLeg.rotationPointX * f5, -this.LLeg.rotationPointY * f5, -this.LLeg.rotationPointZ * f5);
        this.LLeg.render(f5);
        GlStateManager.popMatrix();
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
