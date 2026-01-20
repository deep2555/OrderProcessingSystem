package com.deepanshu.orderprocessing.domainentity;

public class Items {

	private int itemId;
	private String itemName;

	public Items(int itemId, String itemName) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
	}

	public int getItemId() {
		return itemId;
	}

	public String getItemName() {
		return itemName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Items))
			return false;
		Items item = (Items) obj;
		return itemName.equalsIgnoreCase(item.getItemName());
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return itemName.toLowerCase().hashCode();
	}

}
