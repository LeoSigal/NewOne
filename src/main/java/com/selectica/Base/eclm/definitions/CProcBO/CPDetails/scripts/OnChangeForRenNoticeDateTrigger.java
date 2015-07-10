package com.selectica.Base.eclm.definitions.CProcBO.CPDetails.scripts;

import com.selectica.Base.stubs.CPDetails;
import com.selectica.rcfscripts.AbstractDataWriteScript;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by vshilkin on 24/12/2014.
 */
public class OnChangeForRenNoticeDateTrigger extends AbstractDataWriteScript<Boolean> {
    /*
            <![CDATA[
            var autoRenewal = thisComponent.getParameterValueObject("autoRenewal");
            if (autoRenewal == 'yes') {
                var ed = thisComponent.getParameterValueObject("endDate");
                if (ed != null) {
                    var renNoticePer = Number(thisComponent.getParameterValueObject("renNoticePer"));
                    if (renNoticePer > 0) {
                        edCal = ed.copy();
                        edCal.add(Packages.java.util.Calendar.DATE, -renNoticePer);
                        thisComponent.setDateValue("/renNoticeDate", edCal, false);
                    }
                    else {
                        thisComponent.setValue("/renNoticeDate", "", false);
                    }
                }
                else {
                    thisComponent.setValue("/renNoticeDate", "", false);
                }
            }
            else {
                thisComponent.setValue("/renNoticeDate", "", false);
            }
            ]]>
     */
    @Override
    public Boolean process() throws Exception {
        CPDetails details = getHelper().getCurrentComponentStub();
        String autoRenewal = details.getAutoRenewal();
        if ("yes".equals(autoRenewal)) {
            Date end = details.getEndDate();
            if (end != null) {
                Integer renNoticePeriod = details.getRenNoticePer();
                if (renNoticePeriod != null && renNoticePeriod > 0) {
                    Calendar endCal = Calendar.getInstance();
                    endCal.setTime(end);
                    endCal.add(Calendar.DATE, -renNoticePeriod);
                    details.setRenNoticeDate(endCal.getTime());
                } else {
                    details.setRenNoticeDate(null);
                }
            } else {
                details.setRenNoticeDate(null);
            }
        } else {
            details.setRenNoticeDate(null);
        }
        return true;
    }
}


