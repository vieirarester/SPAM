/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.conversores;

import br.edu.ifpe.garanhuns.spam.conversores.entidade.SampleEntity;
import java.io.Serializable;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Ester
 */
@FacesConverter("generic")

public class GenericConverter implements Converter, Serializable {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
        if (value != null) {
            return this.getAttributesFrom(component).get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent component, Object value) {

        if (value != null && !"".equals(value)) {

            SampleEntity entity = (SampleEntity) value;

            this.addAttribute(component, entity);

            Long id = entity.getId();
            if (id != null) {
                return String.valueOf(id);
            }
        }

        return (String) value;
    }

    protected void addAttribute(UIComponent component, SampleEntity o) {
        String key = o.getId().toString();
        this.getAttributesFrom(component).put(key, o);
    }

    protected Map<String, Object> getAttributesFrom(UIComponent component) {
        return component.getAttributes();
    }

}
