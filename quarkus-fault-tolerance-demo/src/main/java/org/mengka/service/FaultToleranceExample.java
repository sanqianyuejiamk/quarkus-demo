/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 *    Copyright (c) [2018] Payara Foundation and/or its affiliates. All rights reserved.
 * 
 *     The contents of this file are subject to the terms of either the GNU
 *     General Public License Version 2 only ("GPL") or the Common Development
 *     and Distribution License("CDDL") (collectively, the "License").  You
 *     may not use this file except in compliance with the License.  You can
 *     obtain a copy of the License at
 *     https://github.com/payara/Payara/blob/master/LICENSE.txt
 *     See the License for the specific
 *     language governing permissions and limitations under the License.
 * 
 *     When distributing the software, include this License Header Notice in each
 *     file and include the License file at glassfish/legal/LICENSE.txt.
 * 
 *     GPL Classpath Exception:
 *     The Payara Foundation designates this particular file as subject to the "Classpath"
 *     exception as provided by the Payara Foundation in the GPL Version 2 section of the License
 *     file that accompanied this code.
 * 
 *     Modifications:
 *     If applicable, add the following below the License Header, with the fields
 *     enclosed by brackets [] replaced by your own identifying information:
 *     "Portions Copyright [year] [name of copyright owner]"
 * 
 *     Contributor(s):
 *     If you wish your version of this file to be governed by only the CDDL or
 *     only the GPL Version 2, indicate your decision by adding "[Contributor]
 *     elects to include this software in this distribution under the [CDDL or GPL
 *     Version 2] license."  If you don't indicate a single choice of license, a
 *     recipient has the option to distribute your version of this file under
 *     either the CDDL, the GPL Version 2 or to extend the choice of license to
 *     its licensees as provided above.  However, if you add GPL Version 2 code
 *     and therefore, elected the GPL Version 2 license, then the option applies
 *     only if the new code is made subject to such option by the copyright
 *     holder.
 */
package org.mengka.service;

import io.smallrye.mutiny.TimeoutException;
import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.opentracing.Traced;
import javax.enterprise.context.ApplicationScoped;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * [walk through the basics]
 *  https://openliberty.io/blog/2020/06/04/asynchronous-programming-microprofile-fault-tolerance.html?es_p=11968531
 *
 *  https://github.com/eclipse/microprofile-fault-tolerance
 *
 *
 * 【@Asynchronous注解】
 *  annotation to a method makes the method run asynchronously
 *
 *  When the annotated method is called, Fault Tolerance first schedules the method to run later on a different thread, then it returns a CompletionStage or Future.
 *
 *  <img src="https://openliberty.io/img/blog/FT-basic-asynchronous-execution.png"/>
 *
 *  In thread2 to execute method.
 *
 * @author Andrew Pielage <andrew.pielage@payara.fish>
 */
//@Traced
@ApplicationScoped
public class FaultToleranceExample {
    
    @Asynchronous
//    @Bulkhead(value = 3, waitingTaskQueue = 2) // maximum 5 concurrent requests allowed, maximum 8 requests allowed in the waiting queue
//    @CircuitBreaker(requestVolumeThreshold = 3)
    @Fallback(fallbackMethod = "fallback")
    @Retry(retryOn = {RuntimeException.class, TimeoutException.class},
            maxRetries = 1)
    @Timeout(1000)
    public Future<String> demonstrateFaultTolerance() {
        Random random = new Random();
        int sleepTime = random.nextInt(4000);

//        int aa = 2/0;

        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
        
        return CompletableFuture.completedFuture("Success!");
    }
    
    
    public Future<String> fallback() {
        return CompletableFuture.completedFuture("Failure!");
    }
}
