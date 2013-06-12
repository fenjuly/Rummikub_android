package org.xhome_liurongchan.rummikub.validate;

import java.util.Collections;
import java.util.List;

import org.xhome_liurongchan.rummikub.bean.Player;
import org.xhome_liurongchan.rummikub.bean.Rummikub;

/**
 * @author [FeN]July E-mail: newfenjuly@gmail.com
 * @version 创建时间：2013-6-12 下午3:04:46 类说明:isValidate定义的方法的实现
 */
public class isValidateImpl implements isValidate {

	@Override
	public boolean isFirst(Player player) {

		return player.getFlag() == 0; //判断是否破冰
	}

	@Override
	public int isCardValidate(Player player, List<Rummikub> valueList) {

		boolean valueflag = true;
		boolean colorflag = true;
		boolean sortflag = true;

		boolean hasSpecial = false;

		int count = 0;

		int specialnumber = 0;

		int color;
		int value;

		for (Rummikub rummikub : valueList) {
			if (rummikub.getValue() == 104 || rummikub.getValue() == 105) {
				valueList.remove(rummikub); //先判断是否有鬼牌，若有，则先移除
				specialnumber++;
				hasSpecial = true;
			}
		}

		color = valueList.get(0).getColor();
		value = valueList.get(0).getValue();

		for (int i = 0; i < valueList.size(); i++) {  //对相同牌的处理
			if (value != valueList.get(i).getValue()) {
				valueflag = false;
			}
		}

		if (valueflag == true) {
			for (int i = 0; i < valueList.size() - 1; i++) {
				for (int j = i + 1; j < valueList.size(); j++) {
					if (valueList.get(i).getColor() == valueList.get(j)
							.getColor()) {
						colorflag = false;
					}
				}
			}

			if (colorflag == true) {
				if (hasSpecial) {
					if ((specialnumber == 1)
							&& (valueList.size() <= 2 || valueList.size() >= 4)) {
						return count;
					} else if ((specialnumber == 2) && (valueList.size() >= 3)) {
						return count;
					}
				} else {
					if (valueList.size() > 4 || valueList.size() <= 2) {
						return count;
					}
				}
				for (Rummikub rummikub : valueList) {
					count += rummikub.getValue();
				}

			}
		} else { //对顺子做处理
			for (Rummikub rummikub : valueList) {
				if (rummikub.getColor() != color) {
					colorflag = false;
				}
			}

			if (colorflag == true) {
				Collections.sort(valueList);
				if (hasSpecial) {
					if (specialnumber == 1 && valueList.size() <= 1) {
						return count;
					}
				} else if (valueList.size() < 3) {
					return count;
				} else {
					for (int i = 0; i < valueList.size() - 1; i++) {
						if (valueList.get(i).getValue() + 1 != valueList.get(
								i + 1).getValue()) {
							sortflag = false;
						}
					}
					if (sortflag == true) {
						for (Rummikub rummikub : valueList) {
							count += rummikub.getValue();
						}
					}
				}
			}
		}

		return count;
	}

	@Override
	public int firstAction(Player player, List<Rummikub> valueList, int count) {

		int specialnumber = 0;

		int value;

		boolean hasSpecial = false;

		boolean valueflag = true;

		for (Rummikub rummikub : valueList) {
			if (rummikub.getValue() == 104 || rummikub.getValue() == 105) {
				hasSpecial = true;
			}
		}

		Collections.sort(valueList);

		value = valueList.get(0).getValue();

		for (int i = 0; i < valueList.size(); i++) {
			if (value != valueList.get(i).getValue()) {
				valueflag = false;
			}
		}
		if (valueflag == true) {
			if (!hasSpecial) {
				count = isCardValidate(player, valueList);
			} else {
				if (specialnumber == 1) {
					count = isCardValidate(player, valueList)
							+ valueList.get(0).getValue();
				} else {
					count = isCardValidate(player, valueList)
							+ valueList.get(valueList.size() - 2).getValue()
							* 2;
				}
			}
		} else {
			if (!hasSpecial) {
				count = isCardValidate(player, valueList);
			} else {
				if (specialnumber == 1) {
					count = isCardValidate(player, valueList)
							+ valueList.get(valueList.size() - 2).getValue()
							+ valueList.get(valueList.size() - 1).getValue()
							+ 2;
				}
			}
		}
		return count;
	}

	@Override
	public boolean nextAction(Player player, List<Rummikub> valueList) {
		
		return isCardValidate(player, valueList) != 0;
	}
}
