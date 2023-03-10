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

package org.objectweb.cjdbc.scenario.raidb1;

import org.objectweb.cjdbc.common.sql.metadata.MetadataContainer;
import org.objectweb.cjdbc.common.stream.CJDBCStream;
import org.objectweb.cjdbc.common.stream.encoding.Base64;
import org.objectweb.cjdbc.controller.backend.DatabaseBackend;
import org.objectweb.cjdbc.controller.virtualdatabase.VirtualDatabase;
import org.objectweb.cjdbc.scenario.templates.Raidb1Template;

/**
 * This class defines a StaticMetaDataScenario
 * 
 * @author <a href="mailto:Nicolas.Modrzyk@inria.fr">Nicolas Modrzyk </a>
 * @version 1.0
 */
public class StaticMetaDataScenario extends Raidb1Template
{
  public void testDisplayMetadata() throws Exception
  {

    DatabaseBackend backend = mainVdb.getAndCheckBackend("localhost",
        VirtualDatabase.NO_CHECK_BACKEND);

    long start = System.currentTimeMillis();
    MetadataContainer container = backend.getDatabaseStaticMetadata();
    long end = System.currentTimeMillis();
    int bytes = CJDBCStream.countBytes(container);
    System.out.println("It took me :" + (end - start)
        + " ms to collect the data. The object takes up:" + bytes
        + " bytes of memory.");

    String arigatou = "???????????????";
    String result = new String(Base64
        .decode(Base64.encode(arigatou.getBytes())));
    assertEquals(arigatou, result);
  }
}