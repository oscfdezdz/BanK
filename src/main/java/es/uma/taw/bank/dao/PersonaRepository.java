package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer> {

    @Query
    Optional<PersonaEntity> findByNif(@Param("nif") String nif);

    @Query("select p.nombre, p.apellido1, p.apellido2 from EmpresaPersonaEntity ep, PersonaEntity p where ep" +
            ".personaByIdPersona = p and ep.empresaByIdEmpresa.id = :id")
    List<Object[]> personasNombreCompletoPorEmpresa(@Param("id") String id);

    @Query("select p, ep.tipoPersonaRelacionadaByIdTipo.tipo from PersonaEntity p, EmpresaPersonaEntity ep where p = "
            + "ep.personaByIdPersona and ep.empresaByIdEmpresa.id = :id")
    List<Object[]> personasPorEmpresa(@Param("id") String id);

    @Query("select p, d, ec.tipoClienteRelacionadoByIdTipo.tipo, ep.tipoPersonaRelacionadaByIdTipo.tipo from " +
            "PersonaEntity p, DireccionEntity d, EmpresaClienteEntity ec, EmpresaPersonaEntity ep where p.id != " +
            ":personaId and p.id = d.clienteByClienteId.id and p = ec.personaByIdPersona and ec.empresaByIdEmpresa" +
            ".id = :id and p = ep.personaByIdPersona and ep.empresaByIdEmpresa.id = :id")
    List<Object[]> distintasPersonasPorEmpresa(@Param("id") String id, @Param("personaId") String personaId);

    @Query("select p, d, ec.tipoClienteRelacionadoByIdTipo.tipo, ep.tipoPersonaRelacionadaByIdTipo.tipo from " +
            "PersonaEntity p, DireccionEntity d, EmpresaClienteEntity ec, EmpresaPersonaEntity ep where (p.id != " +
            ":personaId and p.id = d.clienteByClienteId.id and p = ec.personaByIdPersona and ec.empresaByIdEmpresa" +
            ".id = :id and p = ep.personaByIdPersona and ep.empresaByIdEmpresa.id = :id) and ((p.dni like CONCAT" +
            "('%', " + ":texto, '%')) or (p.nombre like CONCAT('%', :texto, '%')))")
    List<Object[]> filtrarPersonasPorEmpresaPorDniNombre(@Param("id") String id, @Param("personaId") String personaId
            , @Param("texto") String texto);

    @Query("select p, d, ec.tipoClienteRelacionadoByIdTipo.tipo, ep.tipoPersonaRelacionadaByIdTipo.tipo from " +
            "PersonaEntity p, DireccionEntity d, EmpresaClienteEntity ec, EmpresaPersonaEntity ep where (p.id != " +
            ":personaId and p.id = d.clienteByClienteId.id and p = ec.personaByIdPersona and ec.empresaByIdEmpresa" +
            ".id = :id and p = ep.personaByIdPersona and ep.empresaByIdEmpresa.id = :id) order by p.fechaNacimiento")
    List<Object[]> filtrarPersonasPorEmpresaPorFechaNacimiento(@Param("id") String id,
                                                               @Param("personaId") String personaId);

    @Query("select p, d, ec.tipoClienteRelacionadoByIdTipo.tipo, ep.tipoPersonaRelacionadaByIdTipo.tipo from " +
            "PersonaEntity p, DireccionEntity d, EmpresaClienteEntity ec, EmpresaPersonaEntity ep where (p.id != " +
            ":personaId and p.id = d.clienteByClienteId.id and p = ec.personaByIdPersona and ec.empresaByIdEmpresa" +
            ".id = :id and p = ep.personaByIdPersona and ep.empresaByIdEmpresa.id = :id) and (ep" +
            ".tipoPersonaRelacionadaByIdTipo.tipo in :tipo)")
    List<Object[]> filtrarPersonasPorEmpresaPorTipo(@Param("id") String id, @Param("personaId") String personaId,
                                                    @Param("tipo") List<String> tipo);

    @Query("select p, d, ec.tipoClienteRelacionadoByIdTipo.tipo, ep.tipoPersonaRelacionadaByIdTipo.tipo from " +
            "PersonaEntity p, DireccionEntity d, EmpresaClienteEntity ec, EmpresaPersonaEntity ep where (p.id != " +
            ":personaId and p.id = d.clienteByClienteId.id and p = ec.personaByIdPersona and ec.empresaByIdEmpresa" +
            ".id = :id and p = ep.personaByIdPersona and ep.empresaByIdEmpresa.id = :id) and ((p.dni like CONCAT" +
            "('%', :texto, '%')) or (p.nombre like CONCAT('%', :texto, '%'))) order by p.fechaNacimiento")
    List<Object[]> filtrarPersonasPorEmpresaPorDniNombreFechaNacimiento(@Param("id") String id,
                                                                        @Param("personaId") String personaId, @Param(
            "texto") String texto);

    @Query("select p, d, ec.tipoClienteRelacionadoByIdTipo.tipo, ep.tipoPersonaRelacionadaByIdTipo.tipo from " +
            "PersonaEntity p, DireccionEntity d, EmpresaClienteEntity ec, EmpresaPersonaEntity ep where (p.id != " +
            ":personaId and p.id = d.clienteByClienteId.id and p = ec.personaByIdPersona and ec.empresaByIdEmpresa.id" +
            " = :id and p = ep.personaByIdPersona and ep.empresaByIdEmpresa.id = :id) and ((p.dni like CONCAT('%', " +
            ":texto, '%')) or (p.nombre like CONCAT('%', :texto, '%'))) and (ep.tipoPersonaRelacionadaByIdTipo.tipo " +
            "in :tipo)")
    List<Object[]> filtrarPersonasPorEmpresaPorDniNombreTipo(@Param("id") String id,
                                                             @Param("personaId") String personaId,
                                                             @Param("texto") String texto,
                                                             @Param("tipo") List<String> tipo);

    @Query("select p, d, ec.tipoClienteRelacionadoByIdTipo.tipo, ep.tipoPersonaRelacionadaByIdTipo.tipo from " +
            "PersonaEntity p, DireccionEntity d, EmpresaClienteEntity ec, EmpresaPersonaEntity ep where (p.id != " +
            ":personaId and p.id = d.clienteByClienteId.id and p = ec.personaByIdPersona and ec.empresaByIdEmpresa.id" +
            " = :id and p = ep.personaByIdPersona and ep.empresaByIdEmpresa.id = :id) and (ep" +
            ".tipoPersonaRelacionadaByIdTipo.tipo in :tipo) order by p.fechaNacimiento")
    List<Object[]> filtrarPersonasPorEmpresaPorFechaNacimientoTipo(@Param("id") String id,
                                                                   @Param("personaId") String personaId, @Param("tipo"
    ) List<String> tipo);

    @Query("select p, d, ec.tipoClienteRelacionadoByIdTipo.tipo, ep.tipoPersonaRelacionadaByIdTipo.tipo from " +
            "PersonaEntity p, DireccionEntity d, EmpresaClienteEntity ec, EmpresaPersonaEntity ep where (p.id != " +
            ":personaId and p.id = d.clienteByClienteId.id and p = ec.personaByIdPersona and ec.empresaByIdEmpresa.id" +
            " = :id and p = ep.personaByIdPersona and ep.empresaByIdEmpresa.id = :id) and ((p.dni like CONCAT('%', " +
            ":texto, '%')) or (p.nombre like CONCAT('%', :texto, '%'))) and (ep.tipoPersonaRelacionadaByIdTipo.tipo " +
            "in :tipo) order by p.fechaNacimiento")
    List<Object[]> filtrarPersonasPorEmpresaPorDniNombreFechaNacimientoTipo(@Param("id") String id, @Param("personaId"
    ) String personaId, @Param("texto") String texto, @Param("tipo") List<String> tipo);

    @Query
    List<PersonaEntity> findByNombre(@Param("nombre") String nombre);

    @Query
    List<PersonaEntity> findByNombreAndApellido1(@Param("nombre") String nombre, @Param("apellido1") String apellido1);

    @Query
    List<PersonaEntity> findByNombreAndApellido1AndApellido2(@Param("nombre") String nombre,
                                                             @Param("apellido1") String apellido1,
                                                             @Param("apellido2") String apellido2);
}
