package com.gatech.cs6440.alcoholrecovery.fhir;

import java.util.List;
import java.util.ArrayList;

import org.hl7.fhir.dstu3.model.*;

import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.instance.model.api.IBaseBundle;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.IQuery;
import org.hl7.fhir.dstu3.model.Bundle.BundleEntryComponent;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.util.BundleUtil;
import ca.uhn.fhir.rest.gclient.ReferenceClientParam;
import ca.uhn.fhir.model.api.Include;
import org.hl7.fhir.exceptions.FHIRException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class FhirService {

    private static IGenericClient client;
    private static FhirContext ctx;
    private static Patient patient;
    



    public FhirService(){
        try {
            ctx = FhirContext.forDstu3();
            client = ctx.newRestfulGenericClient("https://apps.hdap.gatech.edu/syntheticmass/baseDstu3/");
        }catch(Throwable e){
            e.printStackTrace();
        }
    }



    /*
    
    
    public List<String> getConditions() {
        List<String> conditions = new ArrayList<>();
        List<String> codes = new ArrayList<>();
        
        Bundle response = client.search().forResource(Condition.class).where(new ReferenceClientParam("patient").hasId(id))
                .returnBundle(Bundle.class).execute();

        for (IBaseResource resource: BundleUtil.toListOfResources(ctx, response)) {

            Condition condition = (Condition) resource;
            if (condition.getCode().getCoding().size() > 0) {
                String code = condition.getCode().getCoding().get(0).getCode();
                if(!codes.contains(code)) {
                    
                    conditions.add(condition.getCode().getCoding().get(0).getDisplay());
                    codes.add(code);
                }
            }
            
        }

        // Load the subsequent pages
        while (response.getLink(IBaseBundle.LINK_NEXT) != null) {
            response = client
                    .loadPage()
                    .next(response)
                    .execute();

            for (IBaseResource resource: BundleUtil.toListOfResources(ctx, response)) {
                Condition condition = (Condition) resource;
                if (condition.getCode().getCoding().size() > 0) {
                    String code = condition.getCode().getCoding().get(0).getCode();
                    if(!codes.contains(code)) {
                        conditions.add(condition.getCode().getCoding().get(0).getDisplay());
                        codes.add(code);
                    }
                }
                
            }
        }
        return conditions;
    }
    
    //Lionic Code
    //Weight = 29463-7
    //Height = 8302-2
    //Returns 0 if observation not found
    
    public float getLionicValue(String loincCode) throws FHIRException {
        Bundle response = client.search()
                  .forResource("Observation")
                  .where(Observation.CODE.exactly().code(loincCode))
                  .returnBundle(Bundle.class)
                  .execute();
        
        List <BundleEntryComponent> e = response.getEntry();
        String p_id = "Patient/"+id;
        
        for(int i=0;i<e.size();i++) {
            Observation o = (Observation)e.get(i).getResource();
            if(o.hasSubject()) {
                if(o.getSubject().getReference().equalsIgnoreCase(p_id)) {
                    if(o.hasValueQuantity()) {
                        try {
                                return (o.getValueQuantity().getValue().floatValue());
                        } 
                        catch (FHIRException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        }
        
        while (response.getLink(Bundle.LINK_NEXT) != null) {

           // load next page
           Bundle nextPage = client.loadPage().next(response).execute();
           List <BundleEntryComponent> e1 = nextPage.getEntry();
            
            for(int i=0;i<e1.size();i++) {
                Observation o1 = (Observation)e1.get(i).getResource();
                if(o1.hasSubject()) {
                    if(o1.getSubject().getReference().equalsIgnoreCase(p_id)) {
                        if(o1.hasValueQuantity()) {
                            try {
                                return (o1.getValueQuantity().getValue().floatValue());
                            } 
                            catch (FHIRException e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                }   
            }
          response = nextPage;
        }

        return 0;
    }

*/
    
    
    public float getLionicValue(String loincCode, String id) throws FHIRException {

        try {
            Bundle response = client.search().forResource(Observation.class).where(new ReferenceClientParam("patient").hasId(id))
                    .returnBundle(Bundle.class).execute();

            for (IBaseResource resource: BundleUtil.toListOfResources(ctx, response)) {
                Observation obs = (Observation) resource;
                String code = obs.getCode().getCoding().get(0).getCode();
                if (code.equals(loincCode)) {
                    return (obs.getValueQuantity().getValue().floatValue());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (float) 0;
    }
    
    public String getCondition(String id) throws FHIRException {

        try {
            Bundle response = client.search().forResource(Condition.class).where(new ReferenceClientParam("patient").hasId(id))
                    .returnBundle(Bundle.class).execute();

            for (IBaseResource resource: BundleUtil.toListOfResources(ctx, response)) {
                Condition condition = (Condition) resource;
                return condition.getCode().getCoding().get(0).getCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "err";
    }

        /**
     * Find the patient with the given ID and return the full name as a
     * single string.
     */
    public String getName(String id) {
        
        patient = client.read().resource(Patient.class).withId(id).execute();
        
        HumanName human = patient.getNameFirstRep();
        String out = human.getNameAsSingleString();
        return out;
        
    }
}

