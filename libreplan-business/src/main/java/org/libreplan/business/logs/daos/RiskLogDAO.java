/*
 * This file is part of LibrePlan
 *
 * Copyright (C) 2013 St. Antoniusziekenhuis
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.libreplan.business.logs.daos;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.libreplan.business.common.daos.IntegrationEntityDAO;
import org.libreplan.business.logs.entities.RiskLog;
import org.libreplan.business.orders.entities.Order;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * DAO for {@link RiskLog}
 * 
 * @author Miciele Ghiorghis <m.ghiorghis@antoniusziekenhuis.nl>
 */
@Repository
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class RiskLogDAO extends IntegrationEntityDAO<RiskLog> implements
        IRiskLogDAO {


    @Override
    public List<RiskLog> getRiskLogs() {
        return list(RiskLog.class);
    }

    @Override
    public List<RiskLog> getByParent(Order order) {
        return getSession() // &line[Database_Session]
                .createCriteria(RiskLog.class)
                .add(Restrictions.eq("project", order))
                .list();
    }

}
