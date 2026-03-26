package org.rbc.service;

import lombok.extern.slf4j.Slf4j;
import org.rbc.ENUM.Messages;
import org.rbc.data.HolidayRequest;
import org.rbc.data.HolidayResponse;
import org.rbc.entity.Holiday;
import org.rbc.exception.HolidayException;
import org.rbc.repository.HolidayRepository;
import org.rbc.util.HolidayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HolidayService {

 HolidayRepository holidayRepository;
 @Autowired
 public HolidayService(HolidayRepository holidayRepository){
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
  opHol=holidayRepository.findAllHolidayByCountry(Messages.ALL.getMessage());
  if(opHol.isPresent()){
   holidayList.addAll(opHol.get());
  }

  holidayResponseList=holidayList.stream().sorted().map(mapper::apply).collect(Collectors.toList());

  return holidayResponseList;
 }

 public HolidayResponse addOrUpdateHoliday(HolidayRequest holidayRequest,boolean update) {
  if(holidayRequest.getCountry()==null||holidayRequest.getCountry().isBlank()){
   holidayRequest.setCountry(Messages.ALL.getMessage());
  }else{
   holidayRequest.setCountry(holidayRequest.getCountry().toUpperCase());
  }
  Holiday holiday=new Holiday(holidayRequest.getName(),holidayRequest.getCountry(),holidayRequest.getDate(), holidayRequest.getState());
  Optional<Holiday> opHol=holidayRepository.findHoliday(holiday.getCountry(),holiday.getName());
  if(update&&opHol.isEmpty()){
  log.error("in update");
   throw new HolidayException(Messages.NO_HOLIDAYS_COUNTRY.getMessage());
  }
  if(!update&& opHol.isPresent()){
   log.error("in add");
   throw new HolidayException(Messages.HOLIDAY_EXIST.getMessage());}
  holiday=holidayRepository.save(holiday);
  return mapper.apply(holiday);
 }

}
