/**
 *      Copyright (C) 2008 10gen Inc.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.mongodb.framework;

import java.util.*;
import java.net.*;

import com.mongodb.*;
import com.mongodb.util.*;


public class Circular {

    public static void main(String[] args) 
        throws UnknownHostException {

        Mongo m = new Mongo( new DBAddress( "127.0.0.1:27017/driver_test_framework" ) );
        DBObject foo = new BasicDBObject();
        DBCollection b = m.getCollection( "b" );
        foo.put( "c", b );
        m.getCollection( "a" ).save( foo );

        foo = new BasicDBObject();
        foo.put( "c", 1 );
        b.save( foo );

        ObjectId id = new ObjectId();
        foo = new BasicDBObject();
        foo.put( "_id", id );
        foo.put( "that", 2 );
        DBRef ref = new DBRef( "c", id );
        foo.put( "thiz", ref );
        m.getCollection( "c" ).save( foo );

    }
}