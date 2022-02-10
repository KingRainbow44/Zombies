package com.kingrainbow44.crafttools;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * CraftItem is an ItemStack wrapper which allows for other methods
 * that aren't usually on an ItemStack class instance.
 */
public class CraftItem extends ItemStack {

    protected NBTItem nbt;

    public CraftItem(Material material) {
        this(material, 1);
    }

    public CraftItem(Material material, int amount) {
        setType(material); setAmount(amount);
    }

    public CraftItem(ItemStack itemStack) {
        this(itemStack.getType(), itemStack.getAmount());
        setItemMeta(itemStack.getItemMeta());
    }

    public void modifyAttribute(Attribute attribute, int value, EquipmentSlot slot) {
        String internalName = attribute.name().toLowerCase().replaceFirst("_", ".");
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), internalName, value, AttributeModifier.Operation.ADD_NUMBER, slot);
        ItemMeta itemMeta = getItemMetaNonNull(); itemMeta.addAttributeModifier(attribute, modifier); setItemMeta(itemMeta);
    }

    public void modifyAttribute(Attribute attribute, double value, EquipmentSlot slot) {
        String internalName = attribute.name().toLowerCase().replaceFirst("_", ".");
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), internalName, value, AttributeModifier.Operation.ADD_NUMBER, slot);
        ItemMeta itemMeta = getItemMetaNonNull(); itemMeta.addAttributeModifier(attribute, modifier); setItemMeta(itemMeta);
    }

    public void setDisplayName(String displayName) {
        ItemMeta itemMeta = getItemMetaNonNull();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        setItemMeta(itemMeta);
    }

    public Recipe toCraftingRecipe(String a, String b, String c, Map<Character, Material> keys) {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.randomKey(), this);
        recipe.shape(a, b, c); keys.forEach(recipe::setIngredient);
        return recipe;
    }

    public NBTItem getNbt() {
        if(nbt == null) {
            nbt = new NBTItem(this, true);
        }
        return nbt;
    }

    public ItemMeta getItemMetaNonNull() {
        return Objects.requireNonNull(this.getItemMeta());
    }

    public static CraftItem convert(ItemStack itemStack) {
        return new CraftItem(itemStack);
    }
}
