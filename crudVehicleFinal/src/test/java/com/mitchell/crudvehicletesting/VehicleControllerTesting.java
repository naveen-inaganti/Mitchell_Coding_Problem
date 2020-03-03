package com.mitchell.crudvehicletesting;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import java.util.Arrays;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.mitchell.crudvehicle.CrudVehicleFinalApplication;
import com.mitchell.crudvehicle.controller.VehicleController;
import com.mitchell.crudvehicle.dao.VehicleRepository;
import com.mitchell.crudvehicle.model.Vehicle;
import com.mitchell.crudvehicle.service.VehicleService;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrudVehicleFinalApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VehicleControllerTesting {
	
	
	private MockMvc mockMvc;
	
	@Mock
	private VehicleService serviceTest;
	
	@MockBean
	private VehicleRepository vehicleDao;
	
	@Autowired
	private VehicleService servicetest;
	
    @InjectMocks
    private VehicleController vehicleController;

    @Before
    public void setup() {
    	
        mockMvc = MockMvcBuilders.standaloneSetup(vehicleController).build();
    }
	
	
	@Test
	public void checkHomePage() throws Exception {
		

		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("home"));
			
	}
	
	@Test
	public void checkSearchResults() throws Exception {

		Vehicle first_v = new Vehicle();
		first_v.setId(110);
		first_v.setMake("sai");
		first_v.setModel("dudo");
		first_v.setYear(2050);
		
		Vehicle	second_v = new Vehicle();
		second_v.setId(210);
		second_v.setMake("sai");
		second_v.setModel("deer");
		second_v.setYear(2050);
		
		when(serviceTest.listAll()).thenReturn(Arrays.asList(first_v,second_v));
		
		
		mockMvc.perform(get("/searchById?vehicleId="))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(status().isOk())
			.andExpect(view().name("showSearchList"))
			.andExpect(model().attribute("listVehicles", hasSize(2)))
			.andExpect(model().attribute("listVehicles",equalTo(Arrays.asList(first_v,second_v))));
		verify(serviceTest, times(1)).listAll();
        verifyNoMoreInteractions(serviceTest);
        
        when(serviceTest.getById(110)).thenReturn(first_v);
		
        mockMvc.perform(get("/searchById?vehicleId=110"))
		.andExpect(status().isOk())
		.andExpect(view().name("showSearchList"))
		.andExpect(model().attribute("listVehicles", hasSize(1)))
		.andExpect(model().attribute("listVehicles",equalTo(Arrays.asList(first_v))));
    
        when(serviceTest.getById(210)).thenReturn(second_v);
	    mockMvc.perform(get("/searchById?vehicleId=210"))
		.andExpect(status().isOk())
		.andExpect(view().name("showSearchList"))
		.andExpect(model().attribute("listVehicles", hasSize(1)))
		.andExpect(model().attribute("listVehicles",equalTo(Arrays.asList(second_v))));
			
	}
	
	
	@Test
	public void checkSearchResultsByMake() throws Exception {

		Vehicle first_v = new Vehicle();
		first_v.setId(110);
		first_v.setMake("ford");
		first_v.setModel("mustang");
		first_v.setYear(2049);
		
		Vehicle	second_v = new Vehicle();
		second_v.setId(210);
		second_v.setMake("maserati");
		second_v.setModel("gtx");
		second_v.setYear(2050);
		
		when(serviceTest.listAll()).thenReturn(Arrays.asList(first_v,second_v));
		
		
		mockMvc.perform(get("/searchByMake?vehicleMake="))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(status().isOk())
			.andExpect(view().name("showSearchList"))
			.andExpect(model().attribute("listVehicles", hasSize(2)))
			.andExpect(model().attribute("listVehicles",equalTo(Arrays.asList(first_v,second_v))));
		verify(serviceTest, times(1)).listAll();
        verifyNoMoreInteractions(serviceTest);
        
        when(serviceTest.getByMake("ford")).thenReturn(first_v);
		
        mockMvc.perform(get("/searchByMake?vehicleMake=ford"))
		.andExpect(status().isOk())
		.andExpect(view().name("showSearchList"))
		.andExpect(model().attribute("listVehicles", hasSize(1)))
		.andExpect(model().attribute("listVehicles",equalTo(Arrays.asList(first_v))));
    
        when(serviceTest.getByMake("maserati")).thenReturn(second_v);
	    mockMvc.perform(get("/searchByMake?vehicleMake=maserati"))
		.andExpect(status().isOk())
		.andExpect(view().name("showSearchList"))
		.andExpect(model().attribute("listVehicles", hasSize(1)))
		.andExpect(model().attribute("listVehicles",equalTo(Arrays.asList(second_v))));
			
	}
	
	
	@Test
	public void checkSearchResultsByModel() throws Exception {

		Vehicle first_v = new Vehicle();
		first_v.setId(110);
		first_v.setMake("ford");
		first_v.setModel("mustang");
		first_v.setYear(2049);
		
		Vehicle	second_v = new Vehicle();
		second_v.setId(210);
		second_v.setMake("maserati");
		second_v.setModel("gtx");
		second_v.setYear(2050);
		
		when(serviceTest.listAll()).thenReturn(Arrays.asList(first_v,second_v));
		
		
		mockMvc.perform(get("/searchByModel?vehicleModel="))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(status().isOk())
			.andExpect(view().name("showSearchList"))
			.andExpect(model().attribute("listVehicles", hasSize(2)))
			.andExpect(model().attribute("listVehicles",equalTo(Arrays.asList(first_v,second_v))));
		verify(serviceTest, times(1)).listAll();
        verifyNoMoreInteractions(serviceTest);
        
        when(serviceTest.getByModel("mustang")).thenReturn(first_v);
		
        mockMvc.perform(get("/searchByModel?vehicleModel=mustang"))
		.andExpect(status().isOk())
		.andExpect(view().name("showSearchList"))
		.andExpect(model().attribute("listVehicles", hasSize(1)))
		.andExpect(model().attribute("listVehicles",equalTo(Arrays.asList(first_v))));
    
        when(serviceTest.getByModel("gtx")).thenReturn(second_v);
	    mockMvc.perform(get("/searchByModel?vehicleModel=gtx"))
		.andExpect(status().isOk())
		.andExpect(view().name("showSearchList"))
		.andExpect(model().attribute("listVehicles", hasSize(1)))
		.andExpect(model().attribute("listVehicles",equalTo(Arrays.asList(second_v))));

			
	}
	
	@Test
	public void checkSearchResultsByYear() throws Exception {

		Vehicle first_v = new Vehicle();
		first_v.setId(110);
		first_v.setMake("ford");
		first_v.setModel("mustang");
		first_v.setYear(2049);
		
		Vehicle	second_v = new Vehicle();
		second_v.setId(210);
		second_v.setMake("maserati");
		second_v.setModel("gtx");
		second_v.setYear(2050);
		
		when(serviceTest.listAll()).thenReturn(Arrays.asList(first_v,second_v));
		
		
		mockMvc.perform(get("/searchByYear?vehicleYear="))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(status().isOk())
			.andExpect(view().name("showSearchList"))
			.andExpect(model().attribute("listVehicles", hasSize(2)))
			.andExpect(model().attribute("listVehicles",equalTo(Arrays.asList(first_v,second_v))));
		verify(serviceTest, times(1)).listAll();
        verifyNoMoreInteractions(serviceTest);
        
        when(serviceTest.getByYear(2049)).thenReturn(first_v);
		
        mockMvc.perform(get("/searchByYear?vehicleYear=2049"))
		.andExpect(status().isOk())
		.andExpect(view().name("showSearchList"))
		.andExpect(model().attribute("listVehicles", hasSize(1)))
		.andExpect(model().attribute("listVehicles",equalTo(Arrays.asList(first_v))));

    
        when(serviceTest.getByYear(2050)).thenReturn(second_v);
	    mockMvc.perform(get("/searchByYear?vehicleYear=2050"))
		.andExpect(status().isOk())
		.andExpect(view().name("showSearchList"))
		.andExpect(model().attribute("listVehicles", hasSize(1)))
		.andExpect(model().attribute("listVehicles",equalTo(Arrays.asList(second_v))));

			
	}
	
	
	
	@Test
	public void checkUpdate() throws Exception{
		
		Vehicle first_v = new Vehicle();
		first_v.setId(110);
		first_v.setMake("sai");
		first_v.setModel("dudo");
		first_v.setYear(2050);
		
		when(serviceTest.getById(110)).thenReturn(first_v);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/edit/{Id}",110))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		.andExpect(view().name("editVehicle"))
		.andExpect(model().attribute("vehicle",equalTo(first_v)));
	}
	
	@Test
	public void addVehicleTest() {
		
		Vehicle vehicle=getVehicle();
		Mockito.when(vehicleDao.save(vehicle)).thenReturn(vehicle);
		Vehicle returnedFromDb= servicetest.save(vehicle);
		assertEquals(returnedFromDb,vehicle);
		
		
	}
	
	@Test
	public void deleteVehicleTest() {
		
		Vehicle vehicle=getVehicle();
		Mockito.when(vehicleDao.existsById(vehicle.getId())).thenReturn(false);
		assertFalse(vehicleDao.existsById(vehicle.getId()));
		
		
	}
	
	private Vehicle getVehicle() {
		
		Vehicle vehicle = new Vehicle();
		vehicle.setId(120);
		vehicle.setMake("kiaa");
		vehicle.setModel("seltos");
		vehicle.setYear(2025);
		
		
		return vehicle;
	}
	

	
	
}

