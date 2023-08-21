package epam.com.esm.model.facade;

import epam.com.esm.view.dto.request.DtoRequest;
import epam.com.esm.view.dto.response.DtoResponse;

import java.util.List;

public interface BaseFacade<Q extends DtoRequest, A extends DtoResponse> {

    A create(Q req);

    A update(Q req, Long id);

    A findById(Long id);

    A delete(Long id);

    List<A> findAll();
}