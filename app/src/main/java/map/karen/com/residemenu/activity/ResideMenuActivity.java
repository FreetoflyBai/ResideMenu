package map.karen.com.residemenu.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import map.karen.com.residemenu.R;
import map.karen.com.residemenu.fragment.HomeFragment;
import map.karen.com.residemenu.fragment.NeaerFragment;
import map.karen.com.residemenu.fragment.SettingFragment;
import map.karen.com.residemenu.utils.Tools;
import map.karen.com.residemenu.view.ResideMenu;
import map.karen.com.residemenu.view.ResideMenuItem;


/**
 * package: com.project.baikun.activity
 * user:    baikun
 * date:    2015/1/8
 * link:    904869397@qq.com
 */
public class ResideMenuActivity extends BaseActivity implements OnClickListener {

	private ResideMenu resideMenu;
	private ResideMenuItem itemHome;
	private ResideMenuItem itemNear;
	private ResideMenuItem itemSetting;

	private HomeFragment homeFragment;
	private SettingFragment settingFragment;
	private NeaerFragment nearFragment;

	private FragmentManager fragmentManager;
	private LinearLayout allLayout;

	@Override
	int initLayout() {
		return R.layout.activity_residemenu;
	}
	@Override
	void initView() {
		resideMenu = new ResideMenu(this);
		resideMenu.setBackground(R.drawable.slidingpane_background);
		resideMenu.attachToActivity(this);
		resideMenu.setMenuListener(menuListener);
		resideMenu.setScaleValue(0.6f);

		itemHome = new ResideMenuItem(this, R.drawable.left_fragment_near_icon_normal, "item");
		itemNear = new ResideMenuItem(this, R.drawable.left_fragment_near_icon_normal, "item");
		itemSetting = new ResideMenuItem(this, R.drawable.left_fragment_near_icon_normal, "item");

		resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemNear, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemSetting, ResideMenu.DIRECTION_LEFT);

		itemHome.setOnClickListener(this);
		itemNear.setOnClickListener(this);
		itemSetting.setOnClickListener(this);

		allLayout= (LinearLayout) findViewById(R.id.allLayout);
	}

	@Override
	void initClickListener() {

	}

	@Override
	void initLogic() {
		fragmentManager = getSupportFragmentManager();
		setTabSelection(itemHome);

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if(checkDeviceHasNavigationBar(this)){
			allLayout.setPadding(0, 0, 0, getNavigationBarHeight());
		}

	}

	// A method to find height of the status bar
	public int getStatusBarHeight() {
		int result = 0;
		int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

	public int getNavigationBarHeight(){
		int result = 0;
		int resourceId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

	@SuppressLint("NewApi")
	public static boolean checkDeviceHasNavigationBar(Context activity) {

		//通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar
		boolean hasMenuKey = ViewConfiguration.get(activity)
				.hasPermanentMenuKey();
		boolean hasBackKey = KeyCharacterMap
				.deviceHasKey(KeyEvent.KEYCODE_BACK);
		if (!hasMenuKey && !hasBackKey) {
			return true;
		}
		return false;
	}


	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return resideMenu.dispatchTouchEvent(ev);
	}

	private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {

		@Override
		public void openMenu() {
			resideMenu.startAnim(ResideMenuActivity.this);
		}

		@Override
		public void closeMenu() {
		}
	};

	public ResideMenu getResideMenu() {
		return resideMenu;
	}

	@Override
	public void onClick(View arg0) {
		if (arg0 == itemHome) {
			setTabSelection(itemHome);
			resideMenu.closeMenu();
		} else if (arg0 == itemNear) {
			setTabSelection(itemNear);
			resideMenu.closeMenu();
		} else if (arg0 == itemSetting) {
			setTabSelection(itemSetting);
			resideMenu.closeMenu();
		}
	}

	@SuppressLint("NewApi")
	private void setTabSelection(View view) {
		clearSelection();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		hideFragments(transaction);
		if (view == itemHome) {
			itemHome.setBackgroundResource(R.drawable.left_item_selected_bg);
			itemHome.setIcon(R.drawable.left_fragment_near_icon_selected);
			itemHome.setColor(getResources().getColor(R.color.white));
			if (homeFragment == null) {
				homeFragment = new HomeFragment();
				transaction.add(R.id.main_fragment, homeFragment);
			} else {
				transaction.show(homeFragment);
				if (nearFragment != null) {
					transaction.hide(nearFragment);
				}
				if (settingFragment != null) {
					transaction.hide(settingFragment);
				}
			}
		} else if (view == itemNear) {
			itemNear.setBackgroundResource(R.drawable.left_item_selected_bg);
			itemNear.setIcon(R.drawable.left_fragment_near_icon_selected);
			itemNear.setColor(getResources().getColor(R.color.white));
			if (nearFragment == null) {
				nearFragment = new NeaerFragment();
				transaction.add(R.id.main_fragment, nearFragment);
			} else {
				transaction.show(nearFragment);
				if (homeFragment != null) {
					transaction.hide(homeFragment);
				}
				if (settingFragment != null) {
					transaction.hide(settingFragment);
				}
			}
		} else if (view == itemSetting) {
			itemSetting.setBackgroundResource(R.drawable.left_item_selected_bg);
			itemSetting.setIcon(R.drawable.left_fragment_near_icon_selected);
			itemSetting.setColor(getResources().getColor(R.color.white));
			if (settingFragment == null) {
				settingFragment = new SettingFragment();
				transaction.add(R.id.main_fragment, settingFragment);
			} else {
				transaction.show(settingFragment);
				if (homeFragment != null) {
					transaction.hide(homeFragment);
				}
				if (nearFragment != null) {
					transaction.hide(nearFragment);
				}
			}
		}
		transaction.commit();
	}

	private void clearSelection() {
		itemHome.setBackgroundResource(android.R.color.transparent);
		itemHome.setIcon(R.drawable.left_fragment_near_icon_normal);
		itemHome.setColor(getResources().getColor(R.color.item_color));

		itemNear.setBackgroundResource(android.R.color.transparent);
		itemNear.setIcon(R.drawable.left_fragment_near_icon_normal);
		itemNear.setColor(getResources().getColor(R.color.item_color));

		itemSetting.setBackgroundResource(android.R.color.transparent);
		itemSetting.setIcon(R.drawable.left_fragment_near_icon_normal);
		itemSetting.setColor(getResources().getColor(R.color.item_color));

	}

	private void hideFragments(FragmentTransaction transaction) {
		if (homeFragment != null) {
			transaction.hide(homeFragment);
		}
		if (settingFragment != null) {
			transaction.hide(settingFragment);
		}

		if (nearFragment != null) {
			transaction.hide(nearFragment);
		}
	}







}
