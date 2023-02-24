/**
 * C-JDBC: Clustered JDBC.
 * Copyright (C) 2002-2004 French National Institute For Research In Computer
 * Science And Control (INRIA).
 * Contact: c-jdbc@objectweb.org
 * 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation; either version 2.1 of the License, or any later
 * version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 *
 * Initial developer(s): Nicolas Modrzyk.
 * Contributor(s): ______________________.
 */

package org.objectweb.cjdbc.console.text.commands.dbadmin;

import org.objectweb.cjdbc.common.i18n.ConsoleTranslate;
import org.objectweb.cjdbc.common.jmx.mbeans.VirtualDatabaseMBean;
import org.objectweb.cjdbc.console.text.module.VirtualDatabaseAdmin;

/**
 * This class defines a SetCheckpoint
 * 
 * @author <a href="mailto:Nicolas.Modrzyk@inrialpes.fr">Nicolas Modrzyk </a>
 * @version 1.0
 */
public class SetCheckpoint extends AbstractAdminCommand
{

  /**
   * Creates a new <code>SetCheckpoint</code> object
   * 
   * @param module module that owns this commands
   */
  public SetCheckpoint(VirtualDatabaseAdmin module)
  {
    super(module);
  }

  /**
   * @see org.objectweb.cjdbc.console.text.commands.ConsoleCommand#parse(java.lang.String)
   */
  public void parse(String commandText) throws Exception
  {
    commandText = commandText.trim();
    int firstWhiteSpace = commandText.indexOf(" ");
    if (firstWhiteSpace < 0)
    {
      console.printError(getUsage());
      return;
    }
    String backendName = commandText.substring(0, firstWhiteSpace).trim();
    String checkpointName = commandText.substring(firstWhiteSpace,
        commandText.length()).trim();
    if ("".equals(checkpointName))
    {
      console.printError(getUsage());
      return;
    }
    console.println(ConsoleTranslate.get("admin.command.set.checkpoint.echo",
        new String[]{backendName, checkpointName}));
    VirtualDatabaseMBean vdjc = jmxClient.getVirtualDatabaseProxy(dbName, user,
        password);
    vdjc.setBackendLastKnownCheckpoint(backendName, checkpointName);
  }

  /**
   * @see org.objectweb.cjdbc.console.text.commands.ConsoleCommand#getCommandName()
   */
  public String getCommandName()
  {
    return "force checkpoint";
  }

  /**
   * @see org.objectweb.cjdbc.console.text.commands.ConsoleCommand#getCommandDescription()
   */
  public String getCommandDescription()
  {
    return ConsoleTranslate.get("admin.command.set.checkpoint.description");
  }

  /**
   * @see org.objectweb.cjdbc.console.text.commands.ConsoleCommand#getCommandParameters()
   */
  public String getCommandParameters()
  {
    return ConsoleTranslate.get("admin.command.set.checkpoint.params");
  }
}