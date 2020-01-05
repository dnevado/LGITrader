package com.liferay.custom.my.account.web.internal.form.navigator.category;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

import com.liferay.custom.my.account.web.internal.constants.MyAccountFormNavigatorConstants;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorCategory;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorConstants;

@Component(
	property = {"form.navigator.category.order:Integer=30"},
	service = FormNavigatorCategory.class
)
public class UserUserInformationFormNavigatorCategoryExt implements FormNavigatorCategory {
	@Override
	public String getFormNavigatorId() {
		return MyAccountFormNavigatorConstants.CUSTOM_MY_ACCOUNT;
	}
	
	@Override
	public String getKey() {
		return FormNavigatorConstants.CATEGORY_KEY_USER_USER_INFORMATION;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "user-information");
	}
}
