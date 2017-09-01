package themastergeneral.chivalryisdead;

import android.app.Activity;

import com.winsontan520.wversionmanager.library.OnReceiveListener;
import com.winsontan520.wversionmanager.library.WVersionManager;

/**
 * Created by Ryan on 9/1/2017.
 */
public class VersionChecker
{
    public static void runChecker(Activity activity)
    {
        //Version check
        WVersionManager versionManager = new WVersionManager(activity);
        versionManager.setVersionContentUrl(AppData.VersionChecker);    //Update URL
        versionManager.setUpdateUrl(AppData.LatestDownload);            //Download URL
        versionManager.checkVersion();
        versionManager.setOnReceiveListener(new OnReceiveListener()
        {
            @Override
            public boolean onReceive(int status, String result)
            {
                return true;
            }
        });
    }
}
