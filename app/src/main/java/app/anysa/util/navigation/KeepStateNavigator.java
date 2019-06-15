package app.anysa.util.navigation;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigator;
import androidx.navigation.fragment.FragmentNavigator;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Map;

@Navigator.Name("keep_state_fragment")
public class KeepStateNavigator extends Navigator<FragmentNavigator.Destination> {

    private final String TAG = KeepStateNavigator.class.getSimpleName();

    private final Context mContext;
    private final FragmentManager mFragmentManager;
    private final int mContainerId;
    private ArrayDeque<Integer> mBackStack = new ArrayDeque<>();
    private ArrayDeque<String> mBackStackTag = new ArrayDeque<>();

    private Fragment currentFragment;

    public KeepStateNavigator(@NonNull Context context, @NonNull FragmentManager manager, int containerId) {
        this.mContext = context;
        this.mFragmentManager = manager;
        this.mContainerId = containerId;
    }

    public int handleBackPressed() {

        try {
            if (mBackStackTag.size() > 0) {

                for (String s : mBackStackTag) {
                    Log.d(TAG, "handleBackPressed: " + s.trim());
                }

                Fragment frag = mFragmentManager.findFragmentByTag(mBackStackTag.getLast());
                while (currentFragment != null && frag != null && currentFragment.getClass().getName().equals(frag.getClass().getName())) {
                    popBackStack();
                    frag = mFragmentManager.findFragmentByTag(mBackStackTag.getLast());
                }

                if (frag != null) {
                    if (currentFragment != null && currentFragment.getClass().getName().equals(frag.getClass().getName()))
                        return 0;

                    FragmentTransaction ft = mFragmentManager.beginTransaction();
                    for (Fragment fragment : mFragmentManager.getFragments()) {
                        if (!fragment.getClass().getName().equals(frag.getClass().getName())) {
                            FragmentTransaction ft1 = this.mFragmentManager.beginTransaction();
                            ft1.hide(fragment).commitNow();
                        }
                    }
                    ft.show(frag);
//                    ft.setReorderingAllowed(true);
                    ft.commit();
                    currentFragment = frag;

                    return mBackStack.getLast();
                } else {
                    return 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean popBackStack() {
        if (this.mBackStack.isEmpty()) {
            return false;
        } else if (this.mFragmentManager.isStateSaved()) {
            Log.i("FragmentNavigator", "Ignoring popBackStack() call: FragmentManager has already saved its state");
            return false;
        } else {
            boolean popped = false;
            if (mBackStackTag.size() > 0) {
                this.mFragmentManager.popBackStack();

                this.mBackStack.removeLast();
                this.mBackStackTag.removeLast();
                popped = true;
            }
            return popped;
        }
    }

    @NonNull
    public FragmentNavigator.Destination createDestination() {
        return new FragmentNavigator.Destination(this);
    }

    @NonNull
    public Fragment instantiateFragment(@NonNull Context context, @NonNull FragmentManager
            fragmentManager, @NonNull String className, @Nullable Bundle args) {
        return Fragment.instantiate(context, className, args);
    }

    @Nullable
    public NavDestination navigate(@NonNull FragmentNavigator.Destination destination,
                                   @Nullable Bundle args, @Nullable NavOptions navOptions,
                                   @Nullable androidx.navigation.Navigator.Extras navigatorExtras) {
        if (this.mFragmentManager.isStateSaved()) {
            Log.i("FragmentNavigator", "Ignoring navigate() call: FragmentManager has already saved its state");
            return null;
        } else {

            String packageName = this.mContext.getPackageName();
            String className = destination.getClassName();
            if (className.charAt(0) == '.') {
                className = packageName + className;
            }

            Fragment frag = mFragmentManager.findFragmentByTag(className);

//            if (frag instanceof UserProfileFragment
//                    || frag instanceof WalletFragment
//                    || frag instanceof ProfileSettingsNavFragment
//                    || frag instanceof ConsultationsNavFragment)
//                frag = null;

            boolean fragmentCreatedFirstTime = false;
            if (frag == null) {
                frag = this.instantiateFragment(this.mContext, this.mFragmentManager, className, args);
                fragmentCreatedFirstTime = true;
            }

            boolean shouldReplace = false;

//            if (currentFragment != null && currentFragment.getClass().getName().equals(frag.getClass().getName())) {
//                if (currentFragment instanceof ConsultationsNavFragment) {
//                    shouldReplace = true;
//                } else if (!(currentFragment instanceof UserProfileFragment)) {
//                    ((BaseFragment) frag).onBackPressed();
//                    return null;
//                }
//            }

            frag.setArguments(args);

            FragmentTransaction ft = this.mFragmentManager.beginTransaction();

            int enterAnim = navOptions != null ? navOptions.getEnterAnim() : -1;
            int exitAnim = navOptions != null ? navOptions.getExitAnim() : -1;
            int popEnterAnim = navOptions != null ? navOptions.getPopEnterAnim() : -1;
            int popExitAnim = navOptions != null ? navOptions.getPopExitAnim() : -1;
            if (enterAnim != -1 || exitAnim != -1 || popEnterAnim != -1 || popExitAnim != -1) {
                enterAnim = enterAnim != -1 ? enterAnim : 0;
                exitAnim = exitAnim != -1 ? exitAnim : 0;
                popEnterAnim = popEnterAnim != -1 ? popEnterAnim : 0;
                popExitAnim = popExitAnim != -1 ? popExitAnim : 0;
                ft.setCustomAnimations(enterAnim, exitAnim, popEnterAnim, popExitAnim);
            }

            if (shouldReplace) {
                ft.replace(this.mContainerId, frag, className);
            } else if (currentFragment == null && !frag.isAdded()) {
                ft.add(this.mContainerId, frag, className);
            } else {
                for (Fragment fragment : mFragmentManager.getFragments()) {
                    if (fragment.isVisible()) {
                        FragmentTransaction ft1 = this.mFragmentManager.beginTransaction();
                        ft1.hide(fragment).commitNow();
                    }
                }
                if (fragmentCreatedFirstTime && !frag.isAdded()) {
                    ft.add(this.mContainerId, frag, className);
                } else {
                    ft.show(frag);
                }
            }
            currentFragment = frag;

//            ft.replace(this.mContainerId, frag, className);
//            ft.setPrimaryNavigationFragment(frag);
            int destId = destination.getId();
            boolean initialNavigation = this.mBackStack.isEmpty();
            boolean isSingleTopReplacement = navOptions != null &&
                    !initialNavigation && navOptions.shouldLaunchSingleTop()
                    && this.mBackStack.peekLast() == destId;

            boolean isAdded;
            if (initialNavigation) {
                isAdded = true;
            } else if (isSingleTopReplacement) {
                if (this.mBackStack.size() > 1) {
                    this.mFragmentManager.popBackStack();
                    ft.addToBackStack(Integer.toString(destId));
                }

                isAdded = false;
            } else {

//                if (!(frag instanceof SearchFragment) && !(frag instanceof UserProfileFragment)) {
//                    while (mBackStackTag.remove(className)) {
//                        mBackStack.remove(destId);
//                    }
//                }

                try {
                    if (mBackStackTag.size() > 16) {
//                        String userProfileFragmentClassName = UserProfileFragment.class.getName();
//                        if (userProfileFragmentClassName.charAt(0) == '.') {
//                            userProfileFragmentClassName = packageName + userProfileFragmentClassName;
//                        }
//                        this.mBackStackTag.removeFirstOccurrence(userProfileFragmentClassName);
//                        this.mBackStack.removeFirstOccurrence(BottomNavigationView.userProfileFragment);
//                    popBackStack();
                    }
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }

                ft.addToBackStack(Integer.toString(destId));
                isAdded = true;
            }

            if (navigatorExtras instanceof FragmentNavigator.Extras) {
                FragmentNavigator.Extras extras = (FragmentNavigator.Extras) navigatorExtras;
                Iterator var17 = extras.getSharedElements().entrySet().iterator();

                while (var17.hasNext()) {
                    Map.Entry<View, String> sharedElement = (Map.Entry) var17.next();
                    ft.addSharedElement(sharedElement.getKey(), sharedElement.getValue());
                }
            }

//            ft.setReorderingAllowed(true);
            ft.commit();

            if (isAdded) {
                this.mBackStack.add(destId);
                this.mBackStackTag.add(className);
                return destination;
            } else {
                return null;
            }
        }
    }

    @Nullable
    public Bundle onSaveState() {
        Bundle b = new Bundle();
        int[] backStack = new int[this.mBackStack.size()];
        int index = 0;

        Integer id;
        for (Iterator var4 = this.mBackStack.iterator(); var4.hasNext(); backStack[index++] = id) {
            id = (Integer) var4.next();
        }

        String[] backStackTag = new String[this.mBackStackTag.size()];
        int index1 = 0;

        String id1;
        for (Iterator var4 = this.mBackStackTag.iterator(); var4.hasNext(); backStackTag[index1++] = id1) {
            id1 = (String) var4.next();
        }

        b.putIntArray("androidx-nav-fragment:navigator:backStackIds", backStack);
        b.putStringArray("androidx-nav-fragment:navigator:backStackTags", backStackTag);
        return b;
    }

    public void onRestoreState(@Nullable Bundle savedState) {
        if (savedState != null) {
            int[] backStack = savedState.getIntArray("androidx-nav-fragment:navigator:backStackIds");
            if (backStack != null) {
                this.mBackStack.clear();
                int[] var3 = backStack;
                int var4 = backStack.length;

                for (int var5 = 0; var5 < var4; ++var5) {
                    int destId = var3[var5];
                    this.mBackStack.add(destId);
                }
            }

            String[] backStackTag = savedState.getStringArray("androidx-nav-fragment:navigator:backStackTags");
            if (backStackTag != null) {
                this.mBackStackTag.clear();
                String[] var3 = backStackTag;
                int var4 = backStackTag.length;

                for (int var5 = 0; var5 < var4; ++var5) {
                    String tag = var3[var5];
                    this.mBackStackTag.add(tag);
                }
            }
        }

    }
}

