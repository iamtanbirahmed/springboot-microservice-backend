package com.vital.writeservice.service;

import com.vital.writeservice.component.EncryptionKeyComponent;
import com.vital.writeservice.dto.HeartRateDTO;
import com.vital.writeservice.dto.VitalSignDTO;
import com.vital.writeservice.repository.HeartRateRepository;
import com.vital.writeservice.repository.VitalSignRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;



@Service
@AllArgsConstructor
public class EncoderService {

    private EncryptionKeyComponent encryptionKeyComponent;
    private VitalSignRepository vitalSignRepository;
    private HeartRateRepository heartRateRepository;

    /**
     * helper method to transform input data for saving into database
     * @param vitalSignDTO
     * @return
     */

    public UUID saveEncryptedVitalSign(VitalSignDTO vitalSignDTO) {
        String phiValues = vitalSignDTO.getData().getAnxietyLevel() + "#" + vitalSignDTO.getData().getBaselineProgress() + "#" + vitalSignDTO.getData().getCurrentBpm();
        UUID newVitalSignId = UUID.fromString(this.vitalSignRepository.saveEncrypted(
                phiValues,
                vitalSignDTO.getId(),
                vitalSignDTO.getData().getTime(),
                vitalSignDTO.getData().getState().getBatteryLevel(),
                this.encryptionKeyComponent.getVitalSignSecret()));
        System.out.println(newVitalSignId.toString());
        return newVitalSignId;

    }

    /**
     * helper method to transform input data for saving into database
     * @param heartRateDTO
     * @param vitalSignID
     * @param patientId
     * @return
     */

    public UUID saveEncryptedHeartRate(HeartRateDTO heartRateDTO, UUID vitalSignID, Integer patientId) {
        String phiValues = heartRateDTO.getAnxietyLevel() + "#" + heartRateDTO.getHeartRate() + "#" + heartRateDTO.getRrInterval();
        UUID newHeartRateId = UUID.fromString(this.heartRateRepository.saveEncrypted(
                phiValues,
                heartRateDTO.getMotion(),
                patientId,
                heartRateDTO.getTime(),
                vitalSignID,
                this.encryptionKeyComponent.getHeartRateSecret()));
        System.out.println(newHeartRateId.toString());
        return newHeartRateId;

    }
}
