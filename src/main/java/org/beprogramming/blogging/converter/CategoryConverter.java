package org.beprogramming.blogging.converter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.beprogramming.blogging.model.Category;
import org.beprogramming.blogging.service.CategoryService;

@FacesConverter("categoryConverter")
@ManagedBean
@RequestScoped
public class CategoryConverter implements Converter {

	@Inject
	private CategoryService	rep;

	@Override
	public Object getAsObject(final FacesContext context, final UIComponent component, final String value) {

		if (value.trim().equals(""))
			return null;
		try {

			return rep.find(Integer.parseInt(value));
		} catch (final NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid player"));
		}
	}

	@Override
	public String getAsString(final FacesContext context, final UIComponent component, final Object value) {

		if (value == null || value.equals(""))
			return "";

		return String.valueOf(((Category) value).getCategoryID());
	}

}
