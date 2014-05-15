package com.bip.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bip.bean.ActionType;
import com.bip.dao.IBaseDAO;
import com.bip.vo.ActionTypeVO;

@Service
public class ActionTypeService {

	@Autowired
	private IBaseDAO baseDAO;
	
	public List<ActionTypeVO> getALLActionType(){
		List<ActionTypeVO> actiontypevos = new ArrayList<ActionTypeVO>();
		for(ActionType at:baseDAO.getAllSelf(new ActionType(), "t_actiontype")){
			ActionTypeVO vo = new ActionTypeVO();
			vo.setId(at.getId());
			vo.setName(at.getName());
			vo.setDescription(at.getDescription());
			vo.setDate(vo.getDate());
			vo.setDateMark(vo.getDateMark());
			actiontypevos.add(vo);
		}
		return actiontypevos;
	}
}
