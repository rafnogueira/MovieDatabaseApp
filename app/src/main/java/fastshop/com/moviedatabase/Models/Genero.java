package fastshop.com.moviedatabase.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Genero {

    @SerializedName("genres")
    @Expose
    private List<Genero> listGeneros = null;

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;

    public Genero(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genero(List<Genero> listGeneros, Integer id, String name) {
        this.listGeneros = listGeneros;
        this.id = id;
        this.name = name;
    }

    public List<Genero> getListGeneros() {
        return listGeneros;
    }

    public void setListGeneros(List<Genero> listGeneros) {
        this.listGeneros = listGeneros;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
