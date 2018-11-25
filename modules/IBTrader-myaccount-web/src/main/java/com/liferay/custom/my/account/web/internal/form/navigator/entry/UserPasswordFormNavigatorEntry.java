package com.liferay.custom.my.account.web.internal.form.navigator.entry;

import org.osgi.service.component.annotations.Component;

import com.liferay.custom.my.account.web.internal.constants.MyAccountFormNavigatorConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorConstants;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorEntry;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.users.admin.web.servlet.taglib.ui.BaseUserFormNavigatorEntry;

@Component(
	immediate = true,
	property = {"form.navigator.entry.order:Integer=70"},
	service = FormNavigatorEntry.class
)
public class UserPasswordFormNavigatorEntry extends BaseUserFormNavigatorEntry {

	private boolean visible = GetterUtil.getBoolean(PropsUtil.get(MyAccountFormNavigatorConstants.MY_ACCOUNT_PASSWORD_VISIBLE), true);
	
	@Override
	public String getFormNavigatorId() {
		return MyAccountFormNavigatorConstants.CUSTOM_MY_ACCOUNT;
	}

	@Override
	public boolean isVisible(User user, User selUser) {
		return visible;
	}
	
	@Override
	public String getCategoryKey() {
		return FormNavigatorConstants.CATEGORY_KEY_USER_USER_INFORMATION;
	}

	@Override
	public String getKey() {
		return "password";
	}
	
	@Override
	protected String getJspPath() {
		return "/user/password.jsp";
	}
}