package science_hack.itk.itk_drum;

import android.widget.TextView;

import java.util.ArrayList;

public class DataPoint {

    private Integer m_pitch, m_yaw, m_roll, m_acc_x, m_acc_y, m_acc_z, m_gyro_x, m_gyro_y, m_gyro_z;
    private Integer m_pkg_id;

    public DataPoint(ArrayList<Integer> raw){
        if(raw.size() > 9) {
            m_pkg_id= raw.get(0); //Integer.parseInt(list[9], 16);

            m_acc_x = raw.get(1);
            m_acc_y = raw.get(2);
            m_acc_z = raw.get(3);

            m_gyro_x = raw.get(4);
            m_gyro_y = raw.get(5);
            m_gyro_z = raw.get(6);

            m_roll = raw.get(7);
            m_pitch = raw.get(8);
            m_yaw= raw.get(9);
        }
    }

    public Integer pitch() {
        return m_pitch;
    }
    public Integer yaw() {
        return m_yaw;
    }


    public Integer roll() {
        return m_roll;
    }


    public Integer acc_x() {
        return m_acc_x;
    }

    public Integer acc_y() {
        return m_acc_y;
    }


    public Integer acc_z() {
        return m_acc_z;
    }


    public Integer gyro_x() {
        return m_gyro_x;
    }


    public Integer gyro_y() {
        return m_gyro_y;
    }


    public Integer gyro_z() {
        return m_gyro_z;
    }


    public Integer pkg_id() {
        return m_pkg_id;
    }

}
