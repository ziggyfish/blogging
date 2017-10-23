package org.beprogramming.blogging.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

	@Override
	public void validate(final FacesContext context, final UIComponent component, final Object email) throws ValidatorException {

		try {
			final InternetAddress emailAddr = new InternetAddress(email.toString());
			emailAddr.validate();

			System.out.println(emailAddr.getAddress());
		} catch (final AddressException e) {
			final FacesMessage msg = new FacesMessage(" E-mail validation failed.", "Please provide E-mail address in this format: abcd@abc.com");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}

	}

}
