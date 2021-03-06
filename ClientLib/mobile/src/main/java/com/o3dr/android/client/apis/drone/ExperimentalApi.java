package com.o3dr.android.client.apis.drone;

import android.os.Bundle;

import com.o3dr.android.client.Drone;
import com.o3dr.services.android.lib.mavlink.MavlinkMessageWrapper;
import com.o3dr.services.android.lib.model.action.Action;

import static com.o3dr.services.android.lib.drone.action.ExperimentalActions.ACTION_EPM_COMMAND;
import static com.o3dr.services.android.lib.drone.action.ExperimentalActions.ACTION_SEND_MAVLINK_MESSAGE;
import static com.o3dr.services.android.lib.drone.action.ExperimentalActions.ACTION_TRIGGER_CAMERA;
import static com.o3dr.services.android.lib.drone.action.ExperimentalActions.EXTRA_EPM_RELEASE;
import static com.o3dr.services.android.lib.drone.action.ExperimentalActions.EXTRA_MAVLINK_MESSAGE;

/**
 * Created by Fredia Huya-Kouadio on 1/19/15.
 */
public class ExperimentalApi {

    public static void triggerCamera(Drone drone) {
        drone.performAsyncAction(new Action(ACTION_TRIGGER_CAMERA));
    }

    public static void epmCommand(Drone drone, boolean release) {
        Bundle params = new Bundle();
        params.putBoolean(EXTRA_EPM_RELEASE, release);
        Action epmAction = new Action(ACTION_EPM_COMMAND, params);
        drone.performAsyncAction(epmAction);
    }

    /**
     * This is an advanced/low-level method to send raw mavlink to the vehicle.
     * <p/>
     * This method is included as an ‘escape hatch’ to allow developers to make progress if we’ve
     * somehow missed providing some essentential operation in the rest of this API. Callers do
     * not need to populate sysId/componentId/crc in the packet, this method will take care of that
     * before sending.
     * <p/>
     * If you find yourself needing to use this mathod please contact the drone-platform google
     * group and we’ll see if we can support the operation you needed in some future revision of
     * the API.
     *
     * @param messageWrapper A MAVLinkMessage wrapper instance. No need to fill in
     *                       sysId/compId/seqNum - the API will take care of that.
     */
    public static void sendMavlinkMessage(Drone drone, MavlinkMessageWrapper messageWrapper) {
        Bundle params = new Bundle();
        params.putParcelable(EXTRA_MAVLINK_MESSAGE, messageWrapper);
        drone.performAsyncAction(new Action(ACTION_SEND_MAVLINK_MESSAGE, params));
    }
}
