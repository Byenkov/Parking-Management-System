package com.parking.logic;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.tickets.pojo.Ticket;

@XmlRootElement(name = "responseList")
@XmlSeeAlso({ParkingSlot.class})
public class ResponseList {

    private List<Object> list;

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

}
