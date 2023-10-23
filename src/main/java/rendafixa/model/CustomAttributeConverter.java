package rendafixa.model;

import software.amazon.awssdk.enhanced.dynamodb.AttributeConverter;
import software.amazon.awssdk.enhanced.dynamodb.AttributeValueType;
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomAttributeConverter implements AttributeConverter<List<AplicacaoDBModel>> {

    public CustomAttributeConverter() {
    }

    @Override
    public AttributeValue transformFrom(List<AplicacaoDBModel> input) {
        List<AttributeValue> attributeValueList = new ArrayList<>();

        input.stream().map(item -> {
            Map map = new HashMap<String, String>();
            map.put("valor", AttributeValue.fromN(item.getValor().toString()));
            map.put("dataAplicacao", AttributeValue.fromS(item.getDataAplicacao()));

            return AttributeValue.fromM(map);
        });

        return AttributeValue.fromL(attributeValueList);
    }

    @Override
    public List<AplicacaoDBModel> transformTo(AttributeValue input) {
        return input.l().stream()
                .map(item -> new AplicacaoDBModel(item.m().get("dataAplicacao").s(),
                        BigDecimal.valueOf(Double.valueOf(item.m().get("valor").n()))))
                .collect(Collectors.toList());
    }

    @Override
    public EnhancedType<List<AplicacaoDBModel>> type() {
        return EnhancedType.of((Class<List<AplicacaoDBModel>>) (Object) List.class);
    }

    @Override
    public AttributeValueType attributeValueType() {
        return AttributeValueType.L;
    }
}