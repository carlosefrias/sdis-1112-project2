package com.sdistp2.spycounterspy;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

import entidades.Avatar;

public class ItemsOnMap extends ItemizedOverlay<OverlayItem>{

	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private ArrayList<Avatar> avatares = new ArrayList<Avatar>();
	Context mContext;

	public ItemsOnMap(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}

	public ItemsOnMap(Drawable defaultMarker, Context context) {
		this(defaultMarker);
		mContext = context;
	}

	public void addOverlay(OverlayItem overlay, Avatar avatar) {
		avatares.add(avatar);
		mOverlays.add(overlay);
		populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}
	@Override
	protected boolean onTap(int index) {
		Intent intent = new Intent(mContext, ViewEnemyAct.class);
		intent.putExtra("key", avatares.get(0));//O próprio
		intent.putExtra("keyEnemy", avatares.get(index));
		mContext.startActivity(intent);
		return true;
	}
}
