package bitch;
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lehuy
 */
public class Student implements Serializable{
    private String msv;
    private String ten;
    private float tiengAnh, toan, tin;
    private static final long serialVersionUID = 20151107;

    public Student(String msv, String ten, float tiengAnh, float toan, float tin) {
        this.msv = msv;
        this.ten = ten;
        this.tiengAnh = tiengAnh;
        this.toan = toan;
        this.tin = tin;
    }

    public String getMsv() {
        return msv;
    }

    public String getTen() {
        return ten;
    }

    public float getTiengAnh() {
        return tiengAnh;
    }

    public float getToan() {
        return toan;
    }

    public float getTin() {
        return tin;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setTiengAnh(float tiengAnh) {
        this.tiengAnh = tiengAnh;
    }

    public void setToan(float toan) {
        this.toan = toan;
    }

    public void setTin(float tin) {
        this.tin = tin;
    }

    @Override
    public String toString() {
        return "Student{" + "msv=" + msv + ", ten=" + ten + ", tiengAnh=" + tiengAnh + ", toan=" + toan + ", tin=" + tin + '}';
    }
}
