package com.dropbox.test.dropbox.test.convertor;

import com.dropbox.core.v2.users.FullAccount;
import com.dropbox.core.v2.users.Name;
import com.dropbox.test.dropbox.test.dto.dropbox.DropBoxInfoDto;
import com.dropbox.test.dropbox.test.dto.dropbox.DropBoxNameDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FullAccountToDropBoxInfoDto implements Converter<FullAccount, DropBoxInfoDto> {

    @Override
    public DropBoxInfoDto convert(FullAccount currentAccount) {

        DropBoxInfoDto dto = new DropBoxInfoDto();
        dto.setEmail(currentAccount.getEmail());
        dto.setAccountId(currentAccount.getAccountId());
        dto.setCountry(currentAccount.getCountry());
        dto.setProfilePhotoUrl(currentAccount.getProfilePhotoUrl());

        convertName(currentAccount, dto);

        return dto;
    }

    private void convertName(FullAccount currentAccount, DropBoxInfoDto dto) {
        Name name = currentAccount.getName();

        DropBoxNameDto nameDto = new DropBoxNameDto();
        nameDto.setDisplayName(name.getDisplayName());
        nameDto.setFamiliarName(name.getFamiliarName());
        nameDto.setSurname(name.getSurname());
        nameDto.setGivenName(name.getGivenName());

        dto.setNameInfo(nameDto);
    }
}
