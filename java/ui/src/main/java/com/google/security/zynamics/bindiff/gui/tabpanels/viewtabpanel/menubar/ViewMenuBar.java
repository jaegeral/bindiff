package com.google.security.zynamics.bindiff.gui.tabpanels.viewtabpanel.menubar;

import com.google.security.zynamics.bindiff.gui.tabpanels.menubar.HelpMenu;
import com.google.security.zynamics.bindiff.gui.tabpanels.viewtabpanel.ViewTabPanelFunctions;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;

public class ViewMenuBar extends JMenuBar {
  private final JMenu viewMenu;
  private final JMenu modeMenu;
  private final JMenu graphsMenu;
  private final JMenu selectionMenu;
  private final JMenu searchMenu;

  private final JMenu windowMenu;
  private final JMenu helpMenu;

  public ViewMenuBar(final ViewTabPanelFunctions controller) {
    viewMenu = new ViewMenu(controller);
    modeMenu = new ModesMenu(controller);
    graphsMenu = new GraphsMenu(controller);
    selectionMenu = new SelectionMenu(controller);
    searchMenu = new SearchMenu(controller);
    windowMenu = new WindowMenu(controller);
    helpMenu = new HelpMenu(controller);

    add(viewMenu);
    add(modeMenu);
    add(graphsMenu);
    add(selectionMenu);
    add(searchMenu);
    add(windowMenu);
    add(helpMenu);

    removeKeyBindings();
  }

  private void removeKeyBindings() {
    final InputMap iMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    if (iMap == null) {
      return;
    }
    final Object action = iMap.get(KeyStroke.getKeyStroke("F10"));
    final ActionMap actionMap = getActionMap();
    if (actionMap == null || actionMap.getParent() == null) {
      return;
    }
    actionMap.getParent().remove(action);
  }

  public void dispose() {
    ((ViewMenu) viewMenu).dispose();
    ((ModesMenu) modeMenu).dispose();
    ((GraphsMenu) graphsMenu).dispose();
    ((SelectionMenu) selectionMenu).dispose();
    ((SearchMenu) searchMenu).dispose();
    ((WindowMenu) windowMenu).dispose();
  }
}