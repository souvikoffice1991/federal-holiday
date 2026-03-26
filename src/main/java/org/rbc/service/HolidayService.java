package org.rbc.service;

import org.rbc.ENUM.Messages;
import org.rbc.data.HolidayRequest;
import org.rbc.data.HolidayResponse;
import org.rbc.entity.Holiday;
import org.rbc.repository.HolidayRepository;
import org.rbc.util.HolidayMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HolidayService {

 HolidayRepository holidayRepository;
 HolidayService(HolidayRepository holidayRepository){
  this.holidayRepository=holidayRepository;
 }

 HolidayMapper mapper=new HolidayMapper();

 public List<HolidayResponse> getHolidays(String name){

  List<HolidayResponse> holidayResponseList;
  List<Holiday> holidayList=null;
  Optional<List<Holiday>> opHol=holidayRepository.findAllHolidayByCountry(name.toUpperCase());

  if(opHol.isPresent()){
   holidayList=opHol.get();
  }
  opHol=holidayRepository.findAllHolidayByCountry(Messages.ALL.name());
  if(opHol.isPresent()){
   holidayList.addAll(opHol.get());
  }

  holidayResponseList=holidayList.stream().sorted().map(mapper::apply).collect(Collectors.toList());

  return holidayResponseList;
 }

 public HolidayResponse addHoliday(HolidayRequest holidayRequest) {
  if(holidayRequest.getCountry()==null||holidayRequest.getCountry().isBlank()){
   holidayRequest.setCountry(Messages.ALL.name());
  }else{
   holidayRequest.setCountry(holidayRequest.getCountry().toUpperCase());
  }
 Holiday holiday=holidayRepository.save(new Holiday(holidayRequest.getName(),holidayRequest.getCountry(),holidayRequest.getDate(), holidayRequest.getState()));
  return mapper.apply(holiday);
 }

 // public String addHoliday(HolidayRequest holidayRequest)
}
