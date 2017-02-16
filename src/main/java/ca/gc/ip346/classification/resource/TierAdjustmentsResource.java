package ca.gc.ip346.classification.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import ca.gc.ip346.classification.model.TieredFood;

@Path("/tier-adjustments")
@Consumes({ "application/json", "application/xml" })
@Produces({ "application/json", "application/xml" })
public class TierAdjustmentsResource {

	@GET
	public List<TieredFood> getFoodListForTest() {
		
		TieredFood food1 = new TieredFood();
		food1.setName("Crystalized Pineapple");
		food1.setLabel("dried, sweetened");
		food1.setSubGroup("112");
		food1.setTsatTier(1);
		
		TieredFood food2 = new TieredFood();
		food2.setName("Canned Peach");
		food2.setLabel("canned, sweetened");
		food2.setSubGroup("112");
		food2.setTsatTier(1);
		
		TieredFood food3 = new TieredFood();
		food3.setName("Canned Orange Juice");
		food3.setLabel("canned, juice pack");
		food3.setSubGroup("112");
		food3.setTsatTier(1);
		
		TieredFood food4 = new TieredFood();
		food4.setName("Heavy Syrup Pack");
		food4.setLabel("canned, heavy syrup");
		food4.setSubGroup("112");
		
		// create list
		List<TieredFood> list = new ArrayList<TieredFood>();
		list.add(food1);
		list.add(food2);
		list.add(food3);
		list.add(food4);
		return list;		
	}
	
	@POST
	public List<TieredFood> adjustFoodTiers(List<TieredFood> foodList)  {
		try{
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSessionFlags = kContainer.newKieSession("ksession-process-tiers");	
			System.out.println("Started to process tiers.");
					
			foodList.forEach(food->{
				kSessionFlags.insert(food);					
				kSessionFlags.fireAllRules();
				/*
				System.out.println("Food Name: " + food.getName() + 		
						  "\nLabel: " + food.getLabel() + 
						  "\nSub Group: " + food.getSubGroup() + 
						  "\nTsat Tier: " + food.getTsatTier() + 
						  "\nExclusion 1: " + food.getExclusion1() + 
						  "\nSugar Added: " + food.isSugarAdded() + 
						  "\nAdjusted Tier: " + food.getAdjustedTier());
				*/
			});
			System.out.println("Finished processing tiers.");
			
			if ( kSessionFlags != null ) {
				kSessionFlags.dispose();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return foodList;
	}
	
}
