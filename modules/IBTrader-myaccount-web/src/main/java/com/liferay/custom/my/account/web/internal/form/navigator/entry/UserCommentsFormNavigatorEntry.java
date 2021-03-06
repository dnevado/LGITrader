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
	property = {"form.navigator.entry.order:Integer=20"},
	service = FormNavigatorEntry.class
)
public class UserCommentsFormNavigatorEntry extends BaseUserFormNavigatorEntry {
	private boolean visible = GetterUtil.getBoolean(PropsUtil.get(MyAccountFormNavigatorConstants.MY_ACCOUNT_COMMENTS_VISIBLE), true);

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
		return FormNavigatorConstants.CATEGORY_KEY_USER_MISCELLANEOUS;
	}

	@Override
	public String getKey() {
		return "comments";
	}
	
	@Override
	protected String getJspPath() {
		return "/user/comments.jsp";
	}

}
