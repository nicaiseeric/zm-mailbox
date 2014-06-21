/*
 * ***** BEGIN LICENSE BLOCK *****
 * Zimbra Collaboration Suite Server
 * Copyright (C) 2011, 2013 Zimbra, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software Foundation,
 * version 2 of the License.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 * ***** END LICENSE BLOCK *****
 */

package com.zimbra.doc.soap;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

public class Service implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private List<Command> commands = Lists.newLinkedList();

    private String className = null;
    private String namespace = null;
    private String name = null;
    public  String description = null;

    Service(String className, String namespace) {
        this.className = className;
        this.namespace = namespace;
        this.name = namespace.replaceFirst("urn:", "");
    }

    public String getName() {
        return this.name;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getClassName() {
        return this.className;
    }

    public String getDescription() {
        return (this.description == null) ? "" : this.description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public Command getCommand(String namespace, String name) {
        for (Command cmd : commands) {
            if ( (name.equals(cmd.getName())) && (namespace.equals(cmd.getNamespace())) ) {
                return cmd;
            }
        }
        return null;
    }

    public List<Command> getCommands() {
        List<Command> allCommands = Lists.newLinkedList();

        Iterator<Command> cit = this.commands.iterator();
        while (cit.hasNext()) {
            Command    c = cit.next();
            allCommands.add(c);
        }

        Collections.sort(allCommands, new Command.CommandComparator());

        return allCommands;
    }

    public Command addCommand(Command cmd) {
        this.commands.add(cmd);
        return cmd;
    }

    /**
     * Dumps the contents to <code>System.out.println</code>
     */
    public void dump(boolean dumpCommands) {

        System.out.println("Dump service...");
        System.out.println(this);

        if (dumpCommands) {
            System.out.println("Dump commands...");
            Iterator<Command> it = this.commands.iterator();
            while (it.hasNext()) {
                Command c = it.next();

                c.dump();
            }
        }
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();

        buf.append("[service;hashCode=").append(hashCode());
        buf.append(";name=").append(this.getName());
        buf.append(";description=").append(this.getDescription());
        buf.append(";commandCount=").append(this.commands.size());
        buf.append("]");
        return buf.toString();
    }

    public static class ServiceComparator implements java.util.Comparator<Service> {
        @Override
        public int compare(Service o1, Service o2) {
            String n1 = o1.getName();
            String n2 = o2.getName();

            return    n1.compareTo(n2);
        }
    }

}
