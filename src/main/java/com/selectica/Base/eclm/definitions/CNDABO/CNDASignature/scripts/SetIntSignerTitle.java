package com.selectica.Base.eclm.definitions.CNDABO.CNDASignature.scripts;

import com.selectica.Base.stubs.ContractInfoComponent;
import com.selectica.config.Config;
import com.selectica.config.ConfigKeys;
import com.selectica.rcfscripts.AbstractDataWriteScript;
import com.selectica.rcfutils.RCFUserWrapper;

/**
 * Created by vshilkin on 27.04.2015.
 */
public class SetIntSignerTitle extends AbstractDataWriteScript<String> {
    /*
            <![CDATA[
            result = "";
            var ndaIsStandardContract = thisBundle.getParameterValueObjectFromAnyComponent("ReqCNDADetails", "isStandardContract");
            var useEsign = Packages.com.selectica.config.Config.getPropertyAsBoolean(Packages.com.selectica.config.ConfigKeys.ESIGNATURE_ENABLED);
            if (useEsign != null && ndaIsStandardContract != null &&
                    (useEsign.toString().equalsIgnoreCase("true") || useEsign.toString().equalsIgnoreCase("yes")) &&
                    "yes".equalsIgnoreCase(ndaIsStandardContract.toString())) {
                result = root.getValue("user").getUserWrapper().getTitle();
            }
            ]]>
     */
    @Override
    public String process() throws Exception {
        String useEsignProp = Config.getProperty(ConfigKeys.ESIGNATURE_ENABLED);      //@todo move to RCFHelper !

        boolean useEsign = "true".equalsIgnoreCase(useEsignProp);
        ContractInfoComponent info = getHelper().getInfoComponentStub();
        boolean isStandardContract = "yes".equalsIgnoreCase(info.getIsStandardContract());

        if (useEsign && isStandardContract) {
            String cpName = info.getCpName();
            if (cpName != null && !cpName.isEmpty()) {
                RCFUserWrapper userWrapper = getHelper().getRCFUserWrapper();
                return userWrapper.getTitle();
            }

        }
        return null;
    }
}
