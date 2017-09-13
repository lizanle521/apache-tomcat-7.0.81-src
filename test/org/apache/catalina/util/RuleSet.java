package org.apache.catalina.util;

import org.apache.tomcat.util.digester.Digester;
import org.apache.tomcat.util.digester.RuleSetBase;

/**
 * Created by Lizanle on 2017/9/13.
 */
public class RuleSet extends RuleSetBase {
    @Override
    public void addRuleInstances(Digester digester) {
        digester.addObjectCreate("Person","org.apache.catalina.util.Person");
        digester.addSetProperties("Person");
        digester.addObjectCreate("Person/Child","org.apache.catalina.util.Child");
        digester.addSetProperties("Person/Child");
        digester.addSetNext("Person/Child","add");
    }
}
