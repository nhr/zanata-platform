/*
 * Copyright 2012, Red Hat, Inc. and individual contributors as indicated by the
 * @author tags. See the copyright.txt file in the distribution for a full
 * listing of individual contributors.
 * 
 * This is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This software is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this software; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA, or see the FSF
 * site: http://www.fsf.org.
 */
package org.zanata.webtrans.client.view;

import org.zanata.webtrans.client.presenter.LayoutSelectorPresenter;
import org.zanata.webtrans.client.resources.Resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Alex Eng <a href="mailto:aeng@redhat.com">aeng@redhat.com</a>
 *
 */
public class LayoutSelectorView extends PopupPanel implements LayoutSelectorPresenter.Display
{

   private static final LayoutSelectorUiBinder uiBinder = GWT.create(LayoutSelectorUiBinder.class);

   interface LayoutSelectorUiBinder extends UiBinder<Widget, LayoutSelectorView>
   {
   }

   interface Styles extends CssResource
   {
      String mainPanel();
   }

   @UiField
   Styles style;

   @UiField
   HorizontalPanel layoutList;

   @UiField
   Image configImage;

   @UiField
   Resources resources;

   @UiField(provided = true)
   DockPanel defaultLayout;

   @UiField(provided = true)
   DockPanel maximizeWorkspaceLayout;

   public LayoutSelectorView()
   {
      setAutoHideEnabled(false);
      setAnimationEnabled(false);

      initDefaultLayout();
      initMaximiseWorkspaceLayout();

      setWidget(uiBinder.createAndBindUi(this));

      this.setStyleName(style.mainPanel());

      configImage.setResource(resources.viewChoose());
      configImage.addStyleName("pointer");
   }

   private void initDefaultLayout()
   {
      Label optionLabel = new Label("Options");
      Label southLabel = new Label("TM/Glossay/Users");
      Label workspaceLabel = new Label("Workspace");

      defaultLayout = new DockPanel();
      defaultLayout.add(southLabel, DockPanel.SOUTH);
      defaultLayout.add(optionLabel, DockPanel.EAST);
      defaultLayout.add(workspaceLabel, DockPanel.CENTER);

      defaultLayout.setCellHeight(workspaceLabel, "55px");
      defaultLayout.setCellHeight(optionLabel, "55px");

      defaultLayout.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
      defaultLayout.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
   }

   private void initMaximiseWorkspaceLayout()
   {
      Label workspaceLabel = new Label("");

      maximizeWorkspaceLayout = new DockPanel();
      maximizeWorkspaceLayout.add(workspaceLabel, DockPanel.CENTER);
   }

   private int getWidth()
   {
      return 400;
   }

   @Override
   public void showLayoutList(boolean isShowLayoutList)
   {
      layoutList.setVisible(isShowLayoutList);
      if (isShowLayoutList)
      {
         super.setPopupPosition((Window.getClientWidth() - getWidth()) / 2, 13);
      }
      else
      {
         super.setPopupPosition((Window.getClientWidth() - getWidth()) / 2, 13);
      }
   }

   @Override
   public HasClickHandlers getConfigButton()
   {
      return configImage;
   }

   @Override
   public void toggleView()
   {
      if (layoutList.isVisible())
      {
         showLayoutList(false);
      }
      else
      {
         showLayoutList(true);
      }
   }
}
