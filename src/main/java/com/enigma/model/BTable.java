package com.enigma.model;

import com.enigma.utils.Status;

import javax.persistence.*;

@Entity
@Table(name = "m_table")
public class BTable {

    @Id
    @Column(name = "table_num")
    private String tableNum;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;


    public String getTableNum() {
        return tableNum;
    }

    public void setTableNum(String tableNum) {
        this.tableNum = tableNum;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
