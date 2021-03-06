/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
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

package org.drools.workbench.services.verifier.api.client.checks.util;

import java.util.HashMap;

import org.drools.workbench.services.verifier.api.client.index.keys.UUIDKey;

public class Conflicts {

    public final HashMap<UUIDKey, Conflict> keyMap = new HashMap<>();
    private boolean record;

    public Conflicts( final boolean record ) {
        this.record = record;
    }

    public void add( final Conflict conflict ) {
        if ( record ) {
            keyMap.put( conflict.otherUUID(), conflict );
        }
    }

    public Conflict get( final UUIDKey otherUUID ) {
        return keyMap.get( otherUUID );
    }

    public void remove( final Conflict first ) {
        if ( record ) {
            keyMap.remove( first.otherUUID() );
        }
    }
}
