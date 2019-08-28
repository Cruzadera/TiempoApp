package es.iessaladillo.maria.examenmultihilos;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import es.iessaladillo.maria.examenmultihilos.utils.Constants;

public class App extends Application {

    private String CHANNEL_ID = Constants.DEFAULT_CHANNEL_ID;

    @Override
    public void onCreate() {
        super.onCreate();
        createChannel();
    }

    private void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(
                    Context.NOTIFICATION_SERVICE);
            // Normal channel.
            NotificationChannel defaultChannel = new NotificationChannel(
                    Constants.DEFAULT_CHANNEL_ID,
                    getString(R.string.app_main_channel_name),
                    NotificationManager.IMPORTANCE_DEFAULT);
            defaultChannel.setDescription(getString(R.string.app_main_channel_name));
            //noinspection ConstantConditions
            notificationManager.createNotificationChannel(defaultChannel);
            // High priority channel.
            NotificationChannel highPriorityChannel = new NotificationChannel(
                    Constants.HIGH_PRIORITY_CHANNEL_ID,
                    getString(R.string.high_priority_channel_name),
                    NotificationManager.IMPORTANCE_HIGH);
            defaultChannel.setDescription(getString(R.string.high_priority_channel_name));
            notificationManager.createNotificationChannel(highPriorityChannel);
        }
    }
 
}