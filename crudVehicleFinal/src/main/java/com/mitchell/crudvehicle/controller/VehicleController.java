package com.mitchell.crudvehicle.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mitchell.crudvehicle.model.Vehicle;
import com.mitchell.crudvehicle.service.VehicleService;

@Controller
public class VehicleController {

	
		@Autowired
		private VehicleService service;
	
		
//Request Method to Load Home page
		@RequestMapping(value="/")
		public String viewHomePage() {
			return "home";
		}
		
//Request Method for searching vehicle by Id
		@RequestMapping(value="/searchById")
		public String searchById(@RequestParam (value="vehicleId", required=false) Integer Id, Model model) {
			List<Vehicle> listVehicles=null;
			if(Id==null) {
				
				listVehicles = service.listAll();
				
			}
			
			else {
				listVehicles=new ArrayList<Vehicle>();
				for(Vehicle temp:service.listAll()) {
					if(temp.getId()==Id)
					{
						
						listVehicles.add(temp);
					}
				}
			}
			model.addAttribute("listVehicles", listVehicles);
				
			return "showSearchList";
		}
		

//Request Method for searching vehicle by Make	
		@RequestMapping(value="/searchByMake")
		public String searchById(@RequestParam (value="vehicleMake", required=false) String Make, Model model) {
			List<Vehicle> listVehicles=null;
			if(Make.isEmpty()) {
				
				listVehicles = service.listAll();
				
			}
			
			else {
				listVehicles=new ArrayList<Vehicle>();
				for(Vehicle temp:service.listAll()) {
					if(temp.getMake().equals(Make))
					{
						
						listVehicles.add(temp);
					}
				}
			}
			model.addAttribute("listVehicles", listVehicles);
				
			return "showSearchList";
		}
	
//Request Method for searching vehicle by Model	
		@RequestMapping(value="/searchByModel")
		public String searchByModel(@RequestParam (value="vehicleModel", required=false) String vModel, Model model) {
			List<Vehicle> listVehicles=null;
			if(vModel.isEmpty()) {
				
				listVehicles = service.listAll();
				
			}
			
			else {
				listVehicles=new ArrayList<Vehicle>();
				for(Vehicle temp:service.listAll()) {
					if(temp.getModel().equals(vModel))
					{
						
						listVehicles.add(temp);
					}
				}
			}
			model.addAttribute("listVehicles", listVehicles);
				
			return "showSearchList";
		}
		

//Request Method for searching vehicle by Year	
		@RequestMapping(value="/searchByYear")
		public String searchByYear(@RequestParam (value="vehicleYear", required=false) Integer Year, Model model) {
			List<Vehicle> listVehicles=null;
			if(Year==null) {
				
				listVehicles = service.listAll();
				
			}
			
			else {
				
				listVehicles=new ArrayList<Vehicle>();
				for(Vehicle temp:service.listAll()) {
					if(temp.getYear()==Year)
					{
					
						listVehicles.add(temp);
					}
				}
			}
			model.addAttribute("listVehicles", listVehicles);
				
			return "showSearchList";
		}
		
		
//Request Method To Load New Vehicle Page
		@RequestMapping("/newVehicle")
		public String newVehicle(Model model) {
			Vehicle vehicle=new Vehicle();
			model.addAttribute("vehicle",vehicle);
			return "newVehicle";
		}
		
		
//Request Method Handling new Vehicle addition
		@RequestMapping(value="/save",method=RequestMethod.POST)
		public String saveVehicle(@ModelAttribute("vehicle") Vehicle vehicle, BindingResult result, RedirectAttributes redirectAttributes) {
			
				if(service.getById(vehicle.getId())==null) {
					service.save(vehicle);
					redirectAttributes.addFlashAttribute("message", "Insert Failed");
				    redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
				    if (result.hasErrors()) {
				        return "redirect:/newVehicle";
				    }
				    redirectAttributes.addFlashAttribute("message", "Insert Success");
				    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
					return "redirect:/newVehicle";
				}
				else
				{
					redirectAttributes.addFlashAttribute("message", "Id Already Exsists!, Do Update if you want to");
				   redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
				    return "redirect:/newVehicle";
				}
		}
		
		
//Request Method Handling Updating Vehicle
		@RequestMapping(value="/update",method=RequestMethod.POST)
		public String updateVehicle(@ModelAttribute("vehicle") Vehicle vehicle, BindingResult result, RedirectAttributes redirectAttributes) {
			
					service.save(vehicle);
					redirectAttributes.addFlashAttribute("message", "Update Failed");
				    redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
				    if (result.hasErrors()) {
				        return "redirect:/";
				    }
				    redirectAttributes.addFlashAttribute("message", "Update Success");
				    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
					return "redirect:/";
		}
		
		
//Request Method to load Update vehicle Page
		@RequestMapping(value="/edit/{Id}")
		public ModelAndView editVehicle(@PathVariable(name="Id") Integer Id) {
				ModelAndView mv=new ModelAndView("editVehicle");
				Vehicle vehicle= service.getById(Id);
				mv.addObject("vehicle",vehicle);
				
				return mv;
		}
		
		
//Request Method Handling delete By Id
		@RequestMapping(value="/delete/{Id}")
		public String deleteVehicle(@PathVariable(name="Id") Integer Id) {
				service.delete(Id);
				return "redirect:/";
		}
	
	
}
