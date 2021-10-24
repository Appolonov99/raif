package com.example.raif.service;

import com.example.raif.domain.EnumOperation;
import com.example.raif.domain.Socks;
import com.example.raif.dto.RequestDto;
import com.example.raif.repos.SocksRepo;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocksServiceImpl implements SocksService {

    private final SocksRepo socksRepo;

    public SocksServiceImpl(SocksRepo socksRepo) {
        this.socksRepo = socksRepo;
    }

    @Override
    public boolean addSocks(@NonNull RequestDto request) {
        Socks socks = socksRepo.findByColorAndCottonPart(request.getColor(), request.getCottonPart());
        if (socks != null && request.getQuantity() > 0) {
            socks.setQuantity(socks.getQuantity()+request.getQuantity());
            socksRepo.save(socks);
        }
        else {
            socksRepo.save(new Socks(request.getColor(), request.getCottonPart(), request.getQuantity())); }
        return true;
    }

    @Override
    public boolean deleteSocks(@NonNull RequestDto request) {
        Socks socks = socksRepo.findByColorAndCottonPart(request.getColor(), request.getCottonPart());
        if (socks != null && request.getQuantity() > 0 && socks.getQuantity() >= request.getQuantity()) {
            socks.setQuantity(socks.getQuantity()-request.getQuantity());
            socksRepo.save(socks);
        }
        else return false;
        return true;
    }

    @Override
    @Nullable
    public Integer getQuantity(@NonNull String color, @NonNull String operation, @NonNull Integer cottonPart) {
        EnumOperation oper = EnumOperation.getByTitle(operation);
        if (oper == null)
            return null;
        switch (oper) {
            case LESS: {
                List<Socks> socks = socksRepo.findByColorAndCottonPartLessThan(color, cottonPart);
                return socks.stream().mapToInt(Socks::getQuantity).sum();
            }
            case MORE: {
                List<Socks> socks = socksRepo.findByColorAndCottonPartGreaterThan(color, cottonPart);
                return socks.stream().mapToInt(Socks::getQuantity).sum();
            }
            case EQUAL: {
                Socks socks = socksRepo.findByColorAndCottonPart(color, cottonPart);
                return socks != null ? socks.getQuantity() : 0;
            }
            default: {
                return null;
            }
        }

    }
}
