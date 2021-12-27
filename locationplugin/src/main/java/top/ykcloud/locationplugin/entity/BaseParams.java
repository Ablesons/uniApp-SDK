package top.ykcloud.locationplugin.entity;

import com.hdgq.locationlib.entity.ShippingNoteInfo;

import java.io.Serializable;
import java.util.List;

public class BaseParams implements Serializable {
    // 车牌号
    private String vehicleNumber;
    // 司机姓名
    private String driverName;
    // 备注
    private String remark;
    // 运单信息列表
    private List<ShippingNoteInfo> shippingNoteInfos;

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getRemark() {
        return remark;
    }

    public List<ShippingNoteInfo> getShippingNoteInfos() {
        return shippingNoteInfos;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setShippingNoteInfos(List<ShippingNoteInfo> shippingNoteInfos) {
        this.shippingNoteInfos = shippingNoteInfos;
    }
}
