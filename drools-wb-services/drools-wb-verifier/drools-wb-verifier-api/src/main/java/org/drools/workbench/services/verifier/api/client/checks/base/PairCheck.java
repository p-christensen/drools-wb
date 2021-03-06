/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.workbench.services.verifier.api.client.checks.base;

import org.drools.workbench.services.verifier.api.client.checks.DetectConflictingRowsCheck;
import org.drools.workbench.services.verifier.api.client.cache.inspectors.RuleInspector;
import org.drools.workbench.services.verifier.api.client.checks.DetectRedundantRowsCheck;
import org.drools.workbench.services.verifier.api.client.checks.SingleHitCheck;
import org.drools.workbench.services.verifier.api.client.reporting.Issue;
import org.uberfire.commons.validation.PortablePreconditions;

public class PairCheck
        extends CheckBase {

    protected final RuleInspector ruleInspector;
    protected final RuleInspector other;
    private         Issue         issue;

    public RuleInspector getRuleInspector() {
        return ruleInspector;
    }

    public PairCheck( final RuleInspector ruleInspector,
                      final RuleInspector other ) {
        this.ruleInspector = PortablePreconditions.checkNotNull( "ruleInspector", ruleInspector );
        this.other = PortablePreconditions.checkNotNull( "other", other );
    }

    public RuleInspector getOther() {
        return other;
    }

    @Override
    public void check() {
        hasIssues = false;
        issue = null;

        issue = DetectConflictingRowsCheck.check( ruleInspector,
                                                  other );
        if ( issue.hasIssue() ) {
            hasIssues = true;
            return;
        }

        issue = DetectRedundantRowsCheck.check( ruleInspector,
                                                other );
        if ( issue.hasIssue() ) {
            hasIssues = true;
            return;
        }

        issue = SingleHitCheck.check( ruleInspector,
                                      other );
        if ( issue.hasIssue() ) {
            hasIssues = true;
            return;
        }


    }

    @Override
    public Issue getIssue() {
        return issue;
    }
}
