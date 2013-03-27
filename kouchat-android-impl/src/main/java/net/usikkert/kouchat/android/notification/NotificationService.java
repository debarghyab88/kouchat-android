
/***************************************************************************
 *   Copyright 2006-2013 by Christian Ihle                                 *
 *   kontakt@usikkert.net                                                  *
 *                                                                         *
 *   This file is part of KouChat.                                         *
 *                                                                         *
 *   KouChat is free software; you can redistribute it and/or modify       *
 *   it under the terms of the GNU Lesser General Public License as        *
 *   published by the Free Software Foundation, either version 3 of        *
 *   the License, or (at your option) any later version.                   *
 *                                                                         *
 *   KouChat is distributed in the hope that it will be useful,            *
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of        *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU      *
 *   Lesser General Public License for more details.                       *
 *                                                                         *
 *   You should have received a copy of the GNU Lesser General Public      *
 *   License along with KouChat.                                           *
 *   If not, see <http://www.gnu.org/licenses/>.                           *
 ***************************************************************************/

package net.usikkert.kouchat.android.notification;

import net.usikkert.kouchat.Constants;
import net.usikkert.kouchat.android.R;
import net.usikkert.kouchat.android.controller.MainChatController;
import net.usikkert.kouchat.util.Validate;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Service for handling notifications.
 *
 * @author Christian Ihle
 */
public class NotificationService {

    public static final int SERVICE_NOTIFICATION_ID = 1001;

    private final Context context;

    /**
     * Constructor.
     *
     * @param context Context to associate notifications with, and load resources.
     */
    public NotificationService(final Context context) {
        Validate.notNull(context, "Context can not be null");
        this.context = context;
    }

    /**
     * Creates a notification for association with a foreground service.
     *
     * @return A complete notification.
     */
    public Notification createServiceNotification() {
        final Notification notification = createNotification();
        final PendingIntent pendingIntent = createPendingIntent();

        setLatestEventInfo(notification, pendingIntent);

        return notification;
    }

    private Notification createNotification() {
        return new Notification(
                R.drawable.kou_icon_24x24,
                context.getText(R.string.notification_startup), // Text shown when starting KouChat
                System.currentTimeMillis());
    }

    private PendingIntent createPendingIntent() {
        // Used to launch KouChat when clicking on the notification in the drawer
        return PendingIntent.getActivity(context, 0, new Intent(context, MainChatController.class), 0);
    }

    private void setLatestEventInfo(final Notification notification, final PendingIntent pendingIntent) {
        notification.setLatestEventInfo(context,
                Constants.APP_NAME, // First line of the notification in the drawer
                context.getText(R.string.notification_running), // Second line of the notification in the drawer
                pendingIntent);
    }
}
