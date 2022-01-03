package com.dere.codesvalidate.repository;

import com.dere.codesvalidate.models.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class ConfigurationImpl implements ConfigurationRepository {
    @Override
    public <S extends Configuration> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Configuration> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Configuration> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<Configuration> findAll() {
        return null;
    }

    @Override
    public Iterable<Configuration> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Configuration entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Configuration> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Configuration> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Configuration> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Configuration> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Configuration> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends Configuration> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Configuration> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Configuration> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Configuration> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Configuration> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Configuration> boolean exists(Example<S> example) {
        return false;
    }
}
